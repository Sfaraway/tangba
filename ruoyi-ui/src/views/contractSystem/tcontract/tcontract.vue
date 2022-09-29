<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="员工ID" prop="empId">-->
<!--        <el-input-->
<!--          v-model="queryParams.empId"-->
<!--          placeholder="请输入员工ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="客户ID" prop="customerId">-->
<!--        <el-input-->
<!--          v-model="queryParams.customerId"-->
<!--          placeholder="请输入客户ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="合同名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入合同名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="addTime">
        <el-date-picker clearable
                        v-model="queryParams.addTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择添加时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="修改时间" prop="updateTime">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.updateTime"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择修改时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="添加人" prop="addUserId">-->
<!--        <el-input-->
<!--          v-model="queryParams.addUserId"-->
<!--          placeholder="请输入添加人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="修改人" prop="updateUserId">-->
<!--        <el-input-->
<!--          v-model="queryParams.updateUserId"-->
<!--          placeholder="请输入修改人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="0通过，1不通过" prop="accessStu">-->
<!--        <el-input-->
<!--          v-model="queryParams.accessStu"-->
<!--          placeholder="请输入0通过，1不通过"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="印章ID" prop="sealId">-->
<!--        <el-input-->
<!--          v-model="queryParams.sealId"-->
<!--          placeholder="请输入印章ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['contractSystem:tcontract:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['contractSystem:tcontract:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['contractSystem:tcontract:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['contractSystem:tcontract:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tcontractList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="合同ID" align="center" prop="id" />

      <!--      员工ID-->
      <el-table-column label="员工姓名" align="center" prop="ename" />
      <el-table-column label="联系方式" align="center" prop="phone"/>
      <!--     客户ID -->
      <el-table-column label="客户姓名" align="center" prop="cname" />
      <el-table-column label="职位" align="center" prop="job" />
      <el-table-column label="合同名称" align="center" prop="name" />
<!--      <el-table-column label="附件" prop="enclosure" width="100" align="center">-->
<!--        <file-upload v-model="form.enclosure"/>-->
<!--      </el-table-column>-->
      <!--      1:纸质合同 2：电子合同-->
      <el-table-column label="合同类型" align="center" prop="type" >
        <template slot-scope="scope">
          <el-tag   v-if="scope.row.type==1">纸质合同</el-tag>
          <el-tag   v-if="scope.row.type==2">电子合同</el-tag>
        </template>
      </el-table-column>
      <!--      0：启动 1关闭-->
      <el-table-column label="状态" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-switch
            :disabled="hiddenSwitch(scope.row.job)"
            v-model="scope.row.status"
            active-value="0" inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>

        </template>
      </el-table-column>
<!--      <el-table-column label="添加时间" align="center" prop="addTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="添加人" align="center" prop="addUserId" />-->
<!--      <el-table-column label="修改人" align="center" prop="updateUserId" />-->
      <!--       1：未审核 2：审核中 3：该掌中 4：配送中 5已完成-->
      <el-table-column label="合同进度 " align="center" prop="contractStatus" >
        <template slot-scope="scope">
          <el-tag type="info" v-if="scope.row.contractStatus==0">未启用</el-tag>
          <el-tag type="warning"  effect="dark" v-if="scope.row.contractStatus==1">审批中</el-tag>
          <el-tag type="danger"    v-if="scope.row.contractStatus==2">盖章中</el-tag>
          <el-tag   effect="dark"  v-if="scope.row.contractStatus==4">配送中</el-tag>
          <el-tag   type="success" effect="dark"  v-if="scope.row.contractStatus==3">已完成</el-tag>
        </template>
      </el-table-column>
      <!--      0通过，1不通过-->
<!--      <el-table-column label="" align="center" prop="accessStu" />-->
<!--      <el-table-column label="印章ID" align="center" prop="sealId" />-->
<!--      <el-table-column label="盖章否" align="center" prop="sealStatus" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">

        <template slot-scope="scope">
          <el-button
            :disabled="hiddenSwitch(scope.row.job)"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['contractSystem:tcontract:edit']"
          >修改</el-button>
          <el-button
            :disabled="hiddenSwitch(scope.row.job)"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['contractSystem:tcontract:remove']"
          >删除</el-button>
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户名" prop="customerId">
          <!--          <el-input v-model="form.customerId" placeholder="请输入客户的Id" />-->
          <el-select v-model="form.customerId" placeholder="请选择客户">
            <el-option v-for="item in customerOptions"
                       :key="item.value "
                       :label="item.label"
                       :value="item.value">

            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="员工名" prop="empId">
          <el-select v-model="form.empId" placeholder="请选择员工">
            <el-option v-for="item in staffOptions"
                       :key="item.value "
                       :label="item.label"
                       :value="item.value">

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入合同名称" />
        </el-form-item>
        <el-form-item label="1:纸质合同 2：电子合同" prop="type">
          <el-select v-model="form.type" placeholder="请选择">
            <el-option label="纸质合同" value=1> </el-option>
            <el-option label="电子合同" value=2></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="附件">
          <file-upload v-model="form.enclosure"/>
        </el-form-item>
<!--        <el-form-item label="添加时间" prop="addTime">-->
<!--          <el-date-picker clearable-->
<!--                          v-model="form.addTime"-->
<!--                          type="date"-->
<!--                          value-format="yyyy-MM-dd"-->
<!--                          placeholder="请选择添加时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="添加人" prop="addUserId">
          <el-input v-model="form.addUserId=wtf"    readonly />
        </el-form-item>
        <el-form-item label="修改人" prop="updateUserId">
          <el-input v-model="form.updateUserId=wtf"   readonly />
        </el-form-item>







      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTcontract, getTcontract, delTcontract, addTcontract, updateTcontract,getListMapF } from "@/api/contractSystem/tcontract/tcontract";
import {changeContractStatus} from "@/api/contractSystem/tcontract";
import { listCustomer } from "@/api/contractSystem/customer";

import {getUserId} from "@/api/contractSystem/common"
import {listEmp} from "@/api/contractSystem/emp";


export default {
  name: "Tcontract",
  data() {
    return {

      wtf:this.wtf,
      staffOptions:[],
      //员工信息
      staff:[],
      customerOptions:[],
      // fileType:["doc", "xls", "ppt", "txt", "pdf","png", "jpg", "jpeg"],
      //客户信息
      customer:[],
      // 合同类型
      contractType: {
        paper: 1,
        electronic: 2,
      },

      position: {
        staff: 1,
        manager: 2,
        inspecter: 3,
      },

      // 合同进度
      contractStatus : {
        unfinished: 0,
        noApproval: 1,
        checking:   2,
        sealing:    3,
        finished:   4,
        checked:    5,



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
        id: null,
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
      rules: {

      }
    };
  },
  created() {

    this.getStaff();
    this.getCustomer();
    // this.getList();
    this.getEleMapL();

    this.getWTfid()


  },
  methods: {




    getWTfid(){
      this.loading = true;
      getUserId().then(response => {
        this.wtf = response;


        this.loading = false;

      })

    },
    getEleMapL(){

      this.loading = true;
      getListMapF(this.queryParams).then(response => {
        this.tcontractList = response.rows;
        for (const responseElement of this.tcontractList) {
          console.log(responseElement)
        }
        this.total = response.total;
        this.loading = false;
      }).then(()=>{
        for (let i = 0; i < this.customerOptions.length; i++) {
          this.tcontractList[i].label = this.customerOptions[i].label;
          // console.log("dfkasfj："+this.tcontractList[0])


        }
      });
    },
    handleStatusChange(row) {
      console.log(row.status);
      let text = row.status === "0" ? "启用" : "停用";
      let now = row.status;

      this.$modal.confirm('确认要"' + text + '""' + row.name + '"状态吗？').then(function() {

        return changeContractStatus(row.id, now,row.contractStatus);
      }).then(() => {
        this.getEleMapL();
        this.$modal.msgSuccess(text + "成功");          }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      })
    },

//客户信息
    getCustomer() {
      this.loading = true;
      listCustomer(this.queryParams).then(response => {
        this.customer = response.rows;
        for (const customerElem of this.customer) {
          this.customerOptions.push({
            value: customerElem.cId,
            label: customerElem.cname,
          })
        }}).then(()=>{
        console.log(this.customerOptions[0].label)
      })},
//员工信息
    getStaff() {
      this.loading = true;
      listEmp(this.queryParams).then(response => {
        this.staff = response.rows;
        for (const staffElem of this.staff) {
          this.staffOptions.push({
            value: staffElem.eId,
            label: staffElem.ename,
          })
        }}).then(()=>{
        console.log(this.staffOptions[0].label)
      })},
    /** 查询合同列表 */
    getList() {
      this.loading = true;
      listTcontract(this.queryParams).then(response => {
        this.tcontractList = response.rows;
        for (const responseElement of this.tcontractList) {
          console.log(responseElement)
        }
        this.total = response.total;
        this.loading = false;
      }).then(()=>{
        for (let i = 0; i < this.customerOptions.length; i++) {
          this.tcontractList[i].label = this.customerOptions[i].label;



        }
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
      }).catch(function() {
        row.job = row.job === this.position.staff ? this.position.manager: this.position.staff;
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
              console.log(this.form.addUserId)
              // this.getList();
              this.getEleMapL();
            });
          } else {
            addTcontract(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              // this.getList();
              this.getEleMapL();
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
        this.getEleMapL();
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
      if (job == "员工"){
        b = true;
      }
      else{
        b =false;
      }
      return b;





  }

},}

</script>
