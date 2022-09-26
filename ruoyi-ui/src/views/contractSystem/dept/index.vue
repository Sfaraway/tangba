<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="部门名称" prop="dname">
        <el-input
          v-model="queryParams.dname"
          placeholder="请输入部门名称"
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
      <el-form-item label="员工ID" prop="eId">
        <el-input
          v-model="queryParams.eId"
          placeholder="请输入员工ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位ID" prop="jobId">
        <el-input
          v-model="queryParams.jobId"
          placeholder="请输入职位ID"
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
          v-hasPermi="['tangba:dept:add']"
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
          v-hasPermi="['tangba:dept:edit']"
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
          v-hasPermi="['tangba:dept:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['tangba:dept:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="deptId" />
      <el-table-column label="部门名称" align="center" prop="dname" />
      <el-table-column label="添加时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="添加人" align="center" prop="addUserId" />
      <el-table-column label="修改人" align="center" prop="updateUserId" />
      <el-table-column label="员工ID" align="center" prop="eId" />
      <el-table-column label="职位ID" align="center" prop="jobId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['tangba:dept:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tangba:dept:remove']"
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

    <!-- 添加或修改部门对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="部门名称" prop="dname">
          <el-input v-model="form.dname" placeholder="请输入部门名称" />
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
        <el-form-item label="员工ID" prop="eId">
          <el-input v-model="form.eId" placeholder="请输入员工ID" />
        </el-form-item>
        <el-form-item label="职位ID" prop="jobId">
          <el-input v-model="form.jobId" placeholder="请输入职位ID" />
        </el-form-item>
        <el-divider content-position="center">员工信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddTEmp">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteTEmp">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="tEmpList" :row-class-name="rowTEmpIndex" @selection-change="handleTEmpSelectionChange" ref="tEmp">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="账号" prop="eAcount" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.eAcount" placeholder="请输入账号" />
            </template>
          </el-table-column>
          <el-table-column label="姓名" prop="ename" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ename" placeholder="请输入姓名" />
            </template>
          </el-table-column>
          <el-table-column label="电话" prop="tel" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tel" placeholder="请输入电话" />
            </template>
          </el-table-column>
          <el-table-column label="职务ID" prop="jobId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.jobId" placeholder="请输入职务ID" />
            </template>
          </el-table-column>
          <el-table-column label="部门ID" prop="deptId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.deptId" placeholder="请输入部门ID" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="status" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态">
                <el-option
                  v-for="dict in dict.type.sys_normal_disable"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="添加时间" prop="addTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.addTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择添加时间" />
            </template>
          </el-table-column>
          <el-table-column label="添加人" prop="addUserId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.addUserId" placeholder="请输入添加人" />
            </template>
          </el-table-column>
          <el-table-column label="修改人" prop="updateUserId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.updateUserId" placeholder="请输入修改人" />
            </template>
          </el-table-column>
          <el-table-column label="部门" prop="dname" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.dname" placeholder="请选择部门">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="职位" prop="job" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.job" placeholder="请选择职位">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDept, getDept, delDept, addDept, updateDept } from "@/api/contractSystem/dept";

export default {
  name: "Dept",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedTEmp: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 部门表格数据
      deptList: [],
      // 员工表格数据
      tEmpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dname: null,
        addTime: null,
        addUserId: null,
        updateUserId: null,
        eId: null,
        jobId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        dname: [
          { required: true, message: "部门名称不能为空", trigger: "blur" }
        ],
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
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询部门列表 */
    getList() {
      this.loading = true;
      listDept(this.queryParams).then(response => {
        this.deptList = response.rows;
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
        deptId: null,
        dname: null,
        addTime: null,
        updateTime: null,
        addUserId: null,
        updateUserId: null,
        eId: null,
        jobId: null
      };
      this.tEmpList = [];
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
      this.ids = selection.map(item => item.deptId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加部门";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deptId = row.deptId || this.ids
      getDept(deptId).then(response => {
        this.form = response.data;
        this.tEmpList = response.data.tEmpList;
        this.open = true;
        this.title = "修改部门";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.tEmpList = this.tEmpList;
          if (this.form.deptId != null) {
            updateDept(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDept(this.form).then(response => {
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
      const deptIds = row.deptId || this.ids;
      this.$modal.confirm('是否确认删除部门编号为"' + deptIds + '"的数据项？').then(function() {
        return delDept(deptIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 员工序号 */
    rowTEmpIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 员工添加按钮操作 */
    handleAddTEmp() {
      let obj = {};
      obj.eAcount = "";
      obj.ename = "";
      obj.tel = "";
      obj.jobId = "";
      obj.deptId = "";
      obj.status = "";
      obj.addTime = "";
      obj.addUserId = "";
      obj.updateUserId = "";
      obj.dname = "";
      obj.job = "";
      this.tEmpList.push(obj);
    },
    /** 员工删除按钮操作 */
    handleDeleteTEmp() {
      if (this.checkedTEmp.length == 0) {
        this.$modal.msgError("请先选择要删除的员工数据");
      } else {
        const tEmpList = this.tEmpList;
        const checkedTEmp = this.checkedTEmp;
        this.tEmpList = tEmpList.filter(function(item) {
          return checkedTEmp.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleTEmpSelectionChange(selection) {
      this.checkedTEmp = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tangba/dept/export', {
        ...this.queryParams
      }, `dept_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
