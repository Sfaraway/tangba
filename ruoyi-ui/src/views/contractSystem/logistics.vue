<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="ID" prop="id">-->
<!--        <el-input v-model="queryParams.id" placeholder="请输入ID" clearable @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
      <el-form-item label="快递单号" prop="orderNumber">
        <el-input v-model="queryParams.orderNumber" placeholder="请输入快递单号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
<!--      <el-form-item label="员工ID" prop="empId">-->
<!--        <el-input v-model="queryParams.empId" placeholder="请输入员工ID" clearable @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="客户姓名" prop="cname">-->
<!--        <el-input v-model="queryParams.cname" placeholder="请输入客户姓名" clearable @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="客户ID" prop="customerId">-->
<!--        <el-input v-model="queryParams.customerId" placeholder="请输入客户ID" clearable @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
      <el-form-item label="合同名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入合同名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="物流公司" prop="company">
        <el-input v-model="queryParams.company" placeholder="请输入物流公司" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
<!--      <el-form-item label="配送状态" prop="status">-->
<!--        <el-select v-model="queryParams.status" placeholder="请选择配送状态" clearable>-->
<!--          <el-option v-for="dict in dict.type.sys_job_status" :key="dict.value" :label="dict.label"-->
<!--            :value="dict.value" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="添加时间" prop="addTime">-->
<!--        <el-date-picker clearable v-model="queryParams.addTime" type="date" value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择添加时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="添加人id" prop="addUserId">-->
<!--        <el-input v-model="queryParams.addUserId" placeholder="请输入添加人id" clearable @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="修改人id" prop="updateUserId">-->
<!--        <el-input v-model="queryParams.updateUserId" placeholder="请输入修改人id" clearable-->
<!--          @keyup.enter.native="handleQuery" />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['logistic:logistics:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['logistic:logistics:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['logistic:logistics:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['logistic:logistics:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="logisticsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
<!--      <el-table-column label="员工ID" align="center" prop="empId" />-->
      <el-table-column label="员工姓名" align="center" prop="ename" />
<!--      <el-table-column label="客户ID" align="center" prop="customerId" />-->
      <el-table-column label="客户姓名" align="center" prop="cname" />
      <el-table-column label="客户电话" align="center" prop="phone"/>
      <el-table-column label="合同名称" align="center" prop="name" />
      <el-table-column label="物流公司" align="center" prop="company" />
      <el-table-column label="快递单号" align="center" prop="orderNumber" />
            <el-table-column label="配送状态" align="center" prop="status">
        <template slot-scope="scope">
           <el-switch v-model="scope.row.status" active-value="0" inactive-value="1"
           @change="changeStatus(scope.row)">
           </el-switch>
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
<!--      <el-table-column label="添加人ID" align="center" prop="addUserId" />-->
<!--      <el-table-column label="修改人ID" align="center" prop="updateUserId" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['logistic:logistics:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['logistic:logistics:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改物流对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="快递单号" prop="orderNumber" >
          <el-input v-model="form.orderNumber" placeholder="请输入快递单号" />
        </el-form-item>

        <el-form-item label="合同名称" prop="name" label-width="180px">
<!--          <el-input v-model="form.name" placeholder="请输入合同名称" />-->

          <el-select v-model="form.name" placeholder="请选择合同名称">
            <el-option
              v-for="item in contractOption"
              :key="item.id"
              :label="item.name"
              :value="item.name"
              ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物流公司" prop="company">
          <!-- <el-input v-model="form.company" placeholder="请输入物流公司" /> -->
          <el-select v-model="form.company" placeholder="请选择物流公司">
              <el-option
                v-for="item in LogisticOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="配送状态">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.sys_job_status" :key="dict.value" :label="dict.value">{{dict.label}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label="添加时间" prop="addTime">
          <el-date-picker clearable
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择添加时间">
          </el-date-picker>
        </el-form-item> -->
<!--        <el-form-item label="添加人" prop="addUserId" >-->
<!--          <el-input v-model="form.addUserId" placeholder="请输入添加人id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="修改人" prop="updateUserId" v-if="title!='修改物流'">-->
<!--          <el-input v-model="form.updateUserId" placeholder="请输入修改人id" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
  import {
    listLogistics,
    getLogistics,
    delLogistics,
    addLogistics,
    getCE,
    getUserId,
    updateLogistics,
    changeStatus,
    insertEmpCusId,
  } from "@/api/contractSystem/logistics";
  import {listTcontract} from "@/api/contractSystem/tcontract/tcontract";
  import { listUser } from "@/api/system/user";
  import {parseStrEmpty} from "@/utils/ruoyi";

  export default {
    name: "Logistics",
    dicts: ['sys_job_status'],
    data() {
      return {

        LogisticOptions: [{
          value: '顺丰',
          label: '顺丰'
        }, {
          value: '中通',
          label: '中通'
        }, {
          value: '京东',
          label: '京东'
        }],

        //客户和员工姓名，客户电话
        CE:[],


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
        // 物流表格数据
        logisticsList: [],

        //通过的纸质合同数据
        contractsList:[],

        //合同名称选项
        contractOption:[],

        //登录用户信息
        userInfo:[],

        userId:null,

        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,

        // 查询参数
        queryParams: {
          pageNum: 1,
          // pageSize: 11,
          pageSize: 10,
          id: null,
          orderNumber: null,
          empId: null,
          customerId: null,
          name: null,
          company: null,
          status: null,
          addTime: null,
          addUserId: null,
          updateUserId: null,
          // cname:null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {

          orderNumber: [{
            required: true,
            message: "快递单号不能为空",
            trigger: "blur"
          }],
          // empId: [{
          //   required: true,
          //   message: "员工Id不能为空",
          //   trigger: "blur"
          // }],
          // customerId: [{
          //   required: true,
          //   message: "客户Id不能为空",
          //   trigger: "blur"
          // }],
          name: [{
            required: true,
            message: "合同名称不能为空",
            trigger: "blur"
          }],
          company: [{
            required: true,
            message: "物流公司不能为空",
            trigger: "blur"
          }],
          status: [{
           required: true,
            message: "配送状态不能为空",
            trigger: "blur"
          }],
          addTime: [{
            required: true,
            message: "添加时间不能为空",
            trigger: "blur"
          }],
          updateTime: [{
            required: true,
            message: "修改时间不能为空",
            trigger: "blur"
          }],

        }
      };
    },
    created() {
      this.getList();
      this.getContractList();
      // this.getCEP();
      this.getUserInfo();
    },
    methods: {
      /** 查询物流列表 */
      getList() {
        this.loading = true;
        this.getCEP();
        listLogistics(this.queryParams).then(response => {
          this.logisticsList = response.rows;
          for (let i = 0; i < this.logisticsList.length; i++) {
            this.logisticsList[i].ename = this.CE[i].ename;
            this.logisticsList[i].phone = this.CE[i].phone;
            this.logisticsList[i].cname = this.CE[i].cname;
          }
          this.total = response.total;
          this.loading = false;
        });
      },

      //查询登录用户信息
      getUserInfo(){
        this.loading = true;
        getUserId().then(response =>{
          this.userId = response;

        })
        listUser(this.queryParams).then(response => {
          this.userInfo = response.rows;
          for (const userInfoKey in this.userInfo) {
              if (userInfoKey.user_id==this.userId){
                this.userInfo.push(userInfoKey);
              }
          }
          this.total = response.total;
          this.loading = false;
        });
      },

      /** 查询客户和员工列表 */
      getCEP() {
        this.loading = true;
        getCE().then(response => {
          this.CE = response;
          this.loading = false;
        });
      },


      /** 查询合同列表 */
      getContractList() {
        this.loading = true;
        listTcontract(this.queryParams).then(response => {
          this.contractsList = response.rows;
          for (const customerElem of this.contractsList) {
            if (customerElem.type==1&&customerElem.status==="0"&&customerElem.contractStatus==2){
              this.contractOption.push(customerElem);
            }
          }
          this.total = response.total;
          this.loading = false;
        })


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
          orderNumber: null,
          empId: null,
          customerId: null,
          name: null,
          company: null,
          status: "1",
          addTime: null,
          updateTime: null,
          addUserId: null,
          updateUserId: null
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
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        // this.getContractList();
        this.open = true;
        this.title = "添加物流";
      },
      // 配送状态修改
      changeStatus(row) {
         let text = row.status === "0" ? "启用" : "停用";
         let now = row.status;
         let updateUserId = this.userId;
         this.$modal.confirm('确认要"' + text + '""' + row.name + '"状态吗？').then(function() {
           return changeStatus(row.id, now,updateUserId);
         }).then(() => {
           this.getList();
           this.$modal.msgSuccess(text + "成功");          }).catch(function() {
           row.status = row.status === "0" ? "1" : "0";
         });
       },
    /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        // this.getContractList();
        const id = row.id || this.ids
        getLogistics(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改物流";

        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              this.form.updateUserId = this.userId;
              updateLogistics(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();

              });
            } else {
              this.form.addUserId = this.userId;
              insertEmpCusId(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除物流编号为"' + ids + '"的数据项？').then(function() {
          return delLogistics(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('logistic/logistics/export', {
          ...this.queryParams
        }, `logistics_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
