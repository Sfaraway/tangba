<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="员工号" prop="empId">
        <el-input
          v-model="queryParams.empId"
          placeholder="请输入员工的Id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户号" prop="customerId">
        <el-input
          v-model="queryParams.customerId"
          placeholder="请输入客户的Id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合同名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入合同名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间	" prop="enclosure">
        <el-input
          v-model="queryParams.enclosure"
          placeholder="请输入文件路径	"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否启用" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入状态 "
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
      <el-form-item label="添加人" prop="addUserId">
        <el-input
          v-model="queryParams.addUserId"
          placeholder="请输入添加人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="修改人" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入修改人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合同状态 " prop="contractStatus">
        <el-input
          v-model="queryParams.contractStatus"
          placeholder="请输入合同状态 "
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['tcon:contract:add']"
        >新增</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['tcon:contract:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contractList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="员工名" align="center" prop="empId">
         <template slot-scope="scope">
                  <el-tag effect='plain' v-if="scope.row.empId==1">周子豪</el-tag>
                  <el-tag effect='plain' v-if="scope.row.empId==2">吴彦祖</el-tag>
                  <el-tag effect='plain' v-if="scope.row.empId==3">彭于晏</el-tag>
                  <el-tag effect='plain' v-if="scope.row.empId==4">陈冠希</el-tag>
         </template>
      </el-table-column>
<!--      v-bind="customerOptions"-->
<!--      prop="label"-->
      <el-table-column label="客户名" align="center" prop="label"  >
<!--        <template  v-for="item_ in customerOptions">
          {{item_}}
        </template>-->
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="cphone" />
      <el-table-column label="合同名称" align="center" prop="name" />
      <el-table-column label="合同类型" align="center" prop="type" >
        <template slot-scope="scope">
                  <el-tag   v-if="scope.row.type==1">纸质合同</el-tag>
                  <el-tag   v-if="scope.row.type==2">电子合同</el-tag>
                </template>
      </el-table-column>

      <el-table-column label="合同进度 " align="center" prop="contractStatus" >
          <template slot-scope="scope">
               <el-tag type="warning"  effect="dark" v-if="scope.row.contractStatus==0">未通过</el-tag>
               <el-tag type="success"  effect="dark"  v-if="scope.row.contractStatus==2">已通过</el-tag>
               <el-tag type="danger"  effect="dark"  v-if="scope.row.contractStatus==1">盖章中</el-tag>
               </template>
      </el-table-column>

      <el-table-column label="是否通过" align="center" prop="sealStatus" >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.sealStatus"
            active-value="0" inactive-value="1"
            @change="handleStatusChange(scope.row)"
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
import { listContract, getContract, delContract, addContract, updateContract,changeContractStatusTwo } from "@/api/contractSystem/tcontract";
import FileUpload from "@/components/FileUpload/index";
import { listCustomer } from "@/api/contractSystem/customer";
import {changeStatus} from "@/api/contractSystem/logistics";


export default {
  name: "Contract",
  data() {
    return {
      components:{
             FileUpload
           },

      //客户下拉框选项卡
      customerOptions:[],

      //客户信息
      customer:[],

      cname:[],

      fileType:["doc", "xls", "ppt", "txt", "pdf","png", "jpg", "jpeg"],
      // nowTime: this.getNowTime(),
      // 遮罩层
      loading: true,
      dialogVisible :false,
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
      contractList: [],
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
        status: undefined,
        addTime: null,
        addUserId: null,
        updateUserId: null,
        contractStatus: null,
        sealStatus: null,
        sealId: null,
        accessStu: null,
        cphone: null

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        addTime: [
          { required: true, message: "添加时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ],
        addUserId: [
          { required: true, message: "添加人不能为空", trigger: "blur" }
        ],
        updateUserId: [
          { required: true, message: "修改人不能为空", trigger: "blur" }
        ],
        contractStatus: [
          { required: true, message: "合同状态 1：未审核 2：审核中 3：该掌中 4：配送中 5已完成不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getCustomer();
    this.getList();






  },
  methods: {

    getCname(){
      for (const customerOptionsKey in this.customerOptions) {
        this.cname.push(customerOptionsKey.label);
      }
    },



     // //处理默认选中当前日期
    	//     getNowTime() {
    	//        var now = new Date();
    	//        var year = now.getFullYear(); //得到年份
    	//        var month = now.getMonth(); //得到月份
    	//        var date = now.getDate(); //得到日期
    	//        var hour =" 00:00:00"; //默认时分秒 如果传给后台的格式为年月日时分秒，就需要加这个，如若不需要，此行可忽略
    	//        month = month + 1;
    	//        month = month.toString().padStart(2, "0");
    	//        date = date.toString().padStart(2, "0");
    	//        var defaultDate = `${year}-${month}-${date}`;
    	//        console.log(defaultDate)
    	//        return defaultDate;
    	//        this.$set(this.info, "addTime", defaultDate);
    	//     },



    /** 查询合同列表 */
    getList() {
      this.loading = true;
      listContract(this.queryParams).then(response => {
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).then(()=>{
        for (let i = 0; i < this.customerOptions.length; i++) {
          this.contractList[i].label = this.customerOptions[i].label;

        }

      });
    },

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
      })




      // console.log(this.customerOptions["0"].label);
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
        status: "1",
        addTime: null,
        updateTime: null,
        addUserId: null,
        updateUserId: null,
        contractStatus: null
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
    // 附件上传
    imageUploadChange(url){
        this.form.enclosure=url;
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
      getContract(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改合同";
      });
    },
    // 合同状态修改
    handleStatusChange(row) {
      console.log(typeof row.sealStatus);
      let text = row.sealStatus === "0" ? "启用" : "停用";
      let now = row.sealStatus;
      if(now ="0") {
        row.contractStatus = 2
      };
      if (now ='1'){
        row.contractStatus = 1
      }
      this.getList();
      this.$modal.confirm('确认要"' + text + '""' + row.name + '"状态吗？').then(function() {
        console.log(row.sealStatus)
        return changeContractStatusTwo(row.id, now, row.contractStatus);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");          }).catch(function() {
          row.sealStatus = row.sealStatus === "0" ? "1" : "0";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateContract(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContract(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('tcon/contract/export', {
        ...this.queryParams
      }, `contract_${new Date().getTime()}.xlsx`)
    }
}
};
</script>
