<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="员工ID" prop="empId">
        <el-input
          v-model="queryParams.empId"
          placeholder="请输入员工ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户ID" prop="customerId">
        <el-input
          v-model="queryParams.customerId"
          placeholder="请输入客户ID"
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
      <el-form-item label="添加时间" prop="addTime">
        <el-date-picker clearable
                        v-model="queryParams.addTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择添加时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable
                        v-model="queryParams.updateTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择修改时间">
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
      <el-form-item label="0通过，1不通过" prop="accessStu">
        <el-input
          v-model="queryParams.accessStu"
          placeholder="请输入0通过，1不通过"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="印章ID" prop="sealId">
        <el-input
          v-model="queryParams.sealId"
          placeholder="请输入印章ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>-->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          disabled
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
      <!--     客户ID -->
      <el-table-column label="客户姓名" align="center" prop="cname" />
      <el-table-column label="客户电话" align="center" prop="phone" />
      <el-table-column label="合同名称" align="center" prop="name" />
<!--      <el-table-column label="附件" align="center" prop="enclosure" />-->
      <el-table-column label="合同进度" align="center" prop="contractStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.contractStatus===contractStatus.checking" >盖章中</el-tag>
          <el-tag v-if="scope.row.contractStatus===contractStatus.sealing">已完成</el-tag>
        </template>
      </el-table-column>
<!--      合同盖章-->
      <el-table-column label="合同盖章" align="center" prop="contractStatus">
        <template slot-scope="scope">
          <el-switch
            v-if="scope.row.type===contractType.paper"
            :disabled="hiddenSwitch(scope.row.type)"
            v-model="scope.row.contractStatus"
            :active-value="3"
            :inactive-value="2"
            @change="handleContractSealStatusChange(scope.row)"
          ></el-switch>
          <el-button
            v-if="scope.row.type===contractType.electronic"
            type="primary"
            size="mini"
            @click=""
          >
            附件
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="合同类型" align="center" prop="type">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.type===contractType.paper">纸质合同</el-tag>
          <el-tag type="primary" v-if="scope.row.type===contractType.electronic">电子合同</el-tag>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['contractSystem:tcontract:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['contractSystem:tcontract:remove']"
          >删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

<!--     添加或修改合同对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工ID" prop="empId">
          <el-input v-model="form.empId" placeholder="请输入员工ID" />
        </el-form-item>
        <el-form-item label="客户ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户ID" />
        </el-form-item>
        <el-form-item label="合同名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入合同名称" />
        </el-form-item>
        <el-form-item label="附件">
          <file-upload v-model="form.enclosure"/>
        </el-form-item>
        <el-form-item label="添加时间" prop="addTime">
          <el-date-picker clearable
                          v-model="form.addTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="添加人" prop="addUserId">
          <el-input v-model="form.addUserId" placeholder="请输入添加人" />
        </el-form-item>
        <el-form-item label="修改人" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入修改人" />
        </el-form-item>
        <el-form-item label="0通过，1不通过" prop="accessStu">
          <el-input v-model="form.accessStu" placeholder="请输入0通过，1不通过" />
        </el-form-item>
        <el-form-item label="印章ID" prop="sealId">
          <el-input v-model="form.sealId" placeholder="请输入印章ID" />
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
import { listTcontract, getTcontract, delTcontract, addTcontract, updateTcontract, get } from "@/api/contractSystem/tcontract/tcontract";
import { changeContractSealStatus, getListMap} from "@/api/contractSystem/eleseal/contract";

export default {
  name: "Tcontract",
  data() {
    return {
     // 合同类型
      contractType: {
        paper: 1,
        electronic: 2,
      },
      // 合同进度
      contractStatus : {
        //
        unfinished: 0,
        //
        noApproval: 1,
        //
        checking:   2,
        //
        sealing:    3,
        //
        finished:   4,
      },
      //
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
      rules: {
        addTime: [
          { required: true, message: "添加时间不能为空", trigger: "blur" }
        ],
        addUserId: [
          { required: true, message: "添加人不能为空", trigger: "blur" }
        ],
        updateUserId: [
          { required: true, message: "修改人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getListMapLocal();
  },
  methods: {
    getListMapLocal() {
      this.loading = true;
     /* this.queryParams.contractStatus = this.contractStatus.checking;*/
      getListMap(this.queryParams).then(response => {
        this.tcontractList = response.rows;
        console.log(this.tcontractList)
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询合同列表 */
    getList() {
      this.loading = true;
      /*this.queryParams.contractStatus = this.contractStatus.checking;*/
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
    // 合同盖章状态修改
    handleContractSealStatusChange(row) {
      let text = row.contractStatus === this.contractStatus.sealing ? "" : "撤回";
      /*'确认要给' + row.name + text + '盖章吗？'*/
      this.$modal.confirm().then(function() {
        return changeContractSealStatus(row.id, row.contractStatus);
      }).then(() => {
        this.getListMapLocal();
        this.$modal.msgSuccess(text + "盖章成功");
      }).catch(function() {
        row.contractStatus = row.contractStatus === this.contractStatus.sealing ? this.contractStatus.checking: this.contractStatus.sealing;
      });
    },
    hiddenSwitch(type) {
      return type === this.contractType.electronic;
    },
  }
};
</script>
