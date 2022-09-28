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
          v-model="queryParams.cname"
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

      <el-table-column label="客户名" align="center" prop="cname" />
        <el-table-column label="员工名"  align="center" prop="ename" />

      <el-table-column label="联系方式" align="center" prop="phone" />
      <el-table-column label="合同名称" align="center" prop="name" />
      <el-table-column label="合同类型" align="center" prop="type" >
        <template slot-scope="scope">
                  <el-tag   v-if="scope.row.type==1">纸质合同</el-tag>
                  <el-tag   v-if="scope.row.type==2">电子合同</el-tag>
                </template>
      </el-table-column>
      <el-table-column label="添加时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合同进度 " align="center" prop="contractStatus" >
          <template slot-scope="scope">
               <el-tag type="warning"  effect="dark" v-if="scope.row.contractStatus==0">未通过</el-tag>
               <el-tag type="success"  effect="dark"  v-if="scope.row.contractStatus==1">已通过</el-tag>
               <el-tag type="danger"  effect="dark"  v-if="scope.row.contractStatus==2">盖章中</el-tag>
               </template>
      </el-table-column>




      <el-table-column label="状态" align="center" prop="status" width="100">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.status"
                active-value="0" inactive-value="1"
                @change="handleStatusChange(scope.row)"
              ></el-switch>

            </template>
          </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['tcon:contract:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tcon:contract:remove']"
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

        <el-form-item label="员工的Id" prop="empId">
          <el-input v-model="form.empId" placeholder="请输入员工的Id" />
        </el-form-item>
        <el-form-item label="客户的Id" prop="customerId">
<!--          <el-input v-model="form.customerId" placeholder="请输入客户的Id" />-->
          <el-select v-model="form.customerId" placeholder="请选择客户">
            <el-option v-for="item in customerOptions"
                       :key="item.value "
                       :label="item.label"
                       :value="item.value">

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入合同名称" />
        </el-form-item>

        <el-form-item label="状态 1：启动 0关闭" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态 1：启动 0关闭" ></el-input>
        </el-form-item>

        <el-form-item label="1:纸质合同 2：电子合同" prop="type">
                 <el-select v-model="form.type" placeholder="请选择">
                        <el-option label="纸质合同" value=1> </el-option>
                        <el-option label="电子合同" value=2></el-option>
                </el-select>
        </el-form-item>


        <el-form-item label="添加人" prop="addUserId">
          <el-input v-model="form.addUserId" placeholder="请输入添加人" />
        </el-form-item>
        <el-form-item label="修改人" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入修改人" />
        </el-form-item>
         <el-form-item label="附件上传" prop="encourse">
                   <FileUpload :limit="1" :fileSize="1" @input="imageUploadChange" :fileType="fileType" :value="form.encourse" ></FileUpload>
                </el-form-item>
        <el-form-item label="合同状态 " prop="contractStatus">
          <el-select v-model="form.contractStatus" placeholder="请选择">
                 <el-option label="未通过" value=0> </el-option>
                 <el-option label="已通过" value=1></el-option>
                 <el-option label="盖章中" value=2></el-option>
                 </el-select>
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
import { listContract, getContract, delContract, addContract, updateContract,changeContractStatus } from "@/api/contractSystem/tcontract";
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

      phone:[],

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
        phone: null,
        cname: null,

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
        // console.log(response.rows.cphone);
        for (const responseElement of response.rows) {
          console.log(responseElement)

        }
        this.total = response.total;
        this.loading = false;
      }).then(()=>{
        for (let i = 0; i < this.customerOptions.length; i++) {
          this.contractList[i].label = this.customerOptions[i].label;
          console.log(this.customerOptions["1"])
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
      console.log(row.status);
      let text = row.status === "0" ? "启用" : "停用";
      let now = row.status;
      this.$modal.confirm('确认要"' + text + '""' + row.name + '"状态吗？').then(function() {
        return changeContractStatus(row.id, now);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");          }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
      }).then(function (){
        this.getList();
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
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除合同编号为"' + ids + '"的数据项？').then(function() {
        return delContract(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
