<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账号" prop="eAcount">
        <el-input
          v-model="queryParams.eAcount"
          placeholder="请输入账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="ename">
        <el-input
          v-model="queryParams.ename"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['tangba:emp:add']"
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
          v-hasPermi="['tangba:emp:edit']"
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
          v-hasPermi="['tangba:emp:remove']"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['tangba:emp:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="empList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="eId" />
      <el-table-column label="账号" align="center" prop="eAcount" />
      <el-table-column label="姓名" align="center" prop="ename" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column v-if="form.a == 1" label="职务ID" align="center" prop="jobId" />
      <el-table-column v-if="form.a == 1" label="部门ID" align="center" prop="deptId" />

      <el-table-column label="部门" align="center" prop="dname">

      </el-table-column>
      <el-table-column label="职位" align="center" prop="job" />
      <el-table-column label="添加时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
            <el-switch
              v-model="scope.row.status"
              active-value="0"
              inactive-value="1"
              @change="handleStatusChange(scope.row)"
            ></el-switch>
          </template>
      </el-table-column>
      <el-table-column v-if="form.a == 1" label="添加人" align="center" prop="addUserId" />
      <el-table-column v-if="form.a == 1" label="修改人" align="center" prop="updateUserId" />
      <!-- <el-table-column label="部门" align="center" prop="dname">

      </el-table-column>
      <el-table-column label="职位" align="center" prop="job" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['tangba:emp:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tangba:emp:remove']"
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

    <!-- 添加或修改员工对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账号" prop="eAcount">
          <el-input v-model="form.eAcount" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="姓名" prop="ename">
          <el-input v-model="form.ename" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item v-if="form.a == 1" label="职务ID" prop="jobId">
          <el-input v-model="form.jobId" placeholder="请输入职务ID" />
        </el-form-item>
        <el-form-item v-if="form.a == 1" label="部门ID" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门ID" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="form.a == 1" label="添加时间" prop="addTime">
          <el-date-picker clearable
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-if="form.a == 1" label="添加人" prop="addUserId">
          <el-input v-model="form.addUserId" placeholder="请输入添加人" />
        </el-form-item>
        <el-form-item v-if="form.a == 1" label="修改人" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入修改人" />
        </el-form-item>
        <el-form-item label="部门" prop="dname">
          <el-select v-model="form.dname" placeholder="请选择">
                           <el-option label="技术部" value="技术部"> </el-option>
                           <el-option label="销售部" value="销售部"></el-option>
                           <el-option label="财务部" value="财务部"></el-option>
                           </el-select>
        </el-form-item>
        <el-form-item label="职位" prop="job">
          <el-select v-model="form.job" placeholder="请选择">
                           <el-option label="总监" value="总监"> </el-option>
                           <el-option label="经理" value="经理"></el-option>
                           <el-option label="员工" value="员工"></el-option>
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
import { listEmp, getEmp, delEmp, addEmp, updateEmp,changeTEmpStatus } from "@/api/contractSystem/emp";

export default {
  name: "Emp",
  dicts: ['sys_normal_disable'],
  data() {
    return {
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
      // 员工表格数据
      empList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eAcount: null,
        ename: null,
        tel: null,
        jobId: null,
        deptId: null,
        status: null,
        addTime: null,
        addUserId: null,
        updateUserId: null,
        dname: null,
        job: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        eAcount: [
          { required: true, message: "账号不能为空", trigger: "blur" }
        ],
        ename: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        tel: [
          { required: true, message: "电话不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        dname: [
          { required: true, message: "部门不能为空", trigger: "change" }
        ],
        job: [
          { required: true, message: "职位不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询员工列表 */
    getList() {
      this.loading = true;
      listEmp(this.queryParams).then(response => {
        this.empList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.ename + '"用户吗？').then(function() {
        return changeTEmpStatus(row.eId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
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
        eId: null,
        eAcount: null,
        ename: null,
        tel: null,
        jobId: null,
        deptId: null,
        status: "0",
        addTime: null,
        updateTime: null,
        addUserId: null,
        updateUserId: null,
        dname: null,
        job: null
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
      this.ids = selection.map(item => item.eId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const eId = row.eId || this.ids
      getEmp(eId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.eId != null) {
            updateEmp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmp(this.form).then(response => {
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
      const eIds = row.eId || this.ids;
      this.$modal.confirm('是否确认删除员工编号为"' + eIds + '"的数据项？').then(function() {
        return delEmp(eIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tangba/emp/export', {
        ...this.queryParams
      }, `emp_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
