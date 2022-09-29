<template>
  <div class="app-container">



    <el-table v-loading="loading" :data="tcontractList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="合同ID" align="center" prop="id" />
<!--      员工ID-->
      <el-table-column label="员工名" align="center" prop="ename" />
<!--     客户ID -->
      <el-table-column label="客户名" align="center" prop="cname" />
      <el-table-column label="电话号码" align="center" prop="phone" />
      <el-table-column label="合同名称" align="center" prop="name" />
<!--      <el-table-column label="附件" align="center" prop="enclosure" />-->
<!--      1:纸质合同 2：电子合同-->
      <el-table-column label="合同类型" align="center" prop="type" >
        <template slot-scope="scope">
          <el-tag type="success"  v-if="scope.row.type==1">纸质合同</el-tag>
          <el-tag type="success"   v-if="scope.row.type==2">电子合同</el-tag>

        </template>
      </el-table-column>
<!--      0：启动 1关闭-->
      <


<!--       1：未审核 2：审核中 3：该掌中 4：配送中 5已完成-->
      <el-table-column label="合同进度" align="center" prop="contractStatus" >

        <template slot-scope="scope">
          <el-tag type="info" v-if="scope.row.contractStatus==0">未启用</el-tag>
          <el-tag type="warning"  effect="dark" v-if="scope.row.contractStatus==1">审批中</el-tag>
          <el-tag type="danger"  effect="light"  v-if="scope.row.contractStatus==2">盖章中</el-tag>
          <el-tag   effect="dark"  v-if="scope.row.contractStatus==4">配送中</el-tag>
          <el-tag   type="success" effect="dark"  v-if="scope.row.contractStatus==3">已完成</el-tag>
        </template>
      </el-table-column>
<!--      0通过，1不通过-->

      <el-table-column label="是否通过" align="center" prop="accessStu" >
        <template slot-scope="scope">
          <el-switch
            :disabled="hiddenSwitch(scope.row.job)"
            v-model="scope.row.accessStu"
            active-value="0" inactive-value="1"
            @change="handleAccessChange(scope.row)"
          ></el-switch>

        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改合同对话框 -->

  </div>
</template>

<script>
import { listTcontract, getTcontract, delTcontract, addTcontract, updateTcontract,getListMapF } from "@/api/contractSystem/tcontract/tcontract";
import {changeContractStatusTwo} from "@/api/contractSystem/tcontract";


export default {
  name: "Tcontract",
  data() {
    return {
      staffOptions:[],
      //客户信息
      customer:[],
      // 合同类型
      contractType: {
        paper: 1,
        electronic: 2,
      },
      // 合同进度
      contractStatus : {
        unfinished: 0,
        noApproval: 1,
        checking:   2,
        sealing:    3,
        finished:   4,
      },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 合同表格数据
      tcontractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        empId: null,
        customerId: null,
        name: null,
        enclosure: null,
        type: null,
        status: null,
        addTime: null,
        updateTime: null,
        addUserId: null,
        updateUserId: null,
        contractStatus: null,
        accessStu: null,
        sealId: null,
        sealStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      // rules: {
      //   // addTime: [
      //   //   { required: true, message: "添加时间不能为空", trigger: "blur" }
      //   // ],
      //   // addUserId: [
      //   //   { required: true, message: "添加人不能为空", trigger: "blur" }
      //   // ],
      //   // updateUserId: [
      //   //   { required: true, message: "修改人不能为空", trigger: "blur" }
      //   // ],
      // }
    };
  },
  created() {
    // this.getList();
    this.getEleMapL();
  },
  methods: {

    getEleMapL(){

      this.loading = true;
      getListMapF(this.queryParams).then(response => {
        this.tcontractList = response.rows;
        for (const responseElement of this.tcontractList) {
          console.log(responseElement)
        }
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询合同列表 */
    getList() {
      this.loading = true;
      listTcontract(this.queryParams).then(response => {
        this.tcontractList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        empId: null,
        customerId: null,
        name: null,
        enclosure: null,
        type: null,
        status: "0",
        addTime: null,
        updateTime: null,
        addUserId: null,
        updateUserId: null,
        contractStatus: 0,
        accessStu: null,
        sealId: null,
        sealStatus: "0"


      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleAccessChange(row) {
      let text = row.accessStu === "0" ? "启用" : "停用";
      let now = row.accessStu;

      this.$modal.confirm('确认要"' + text + '""' + row.name + '"状态吗？').then(function() {
        console.log(row.sealStatus);
        return changeContractStatusTwo(row.id, now);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess(text + "成功");          }).catch(function() {
        row.sealStatus = row.sealStatus === "0" ? "1" : "0";
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加合同";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTcontract(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改合同";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTcontract(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTcontract(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除合同编号为"' + ids + '"的数据项？').then(function() {
        return delTcontract(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('contractSystem/tcontract/export', {
        ...this.queryParams
      }, `tcontract_${new Date().getTime()}.xlsx`)
    },
    hiddenSwitch(job) {
      let b;
      if (job == "员工" ){
        b = true;
      }
      else{
        b =false;
      }
      return b;





    }

  }
};
</script>
