<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="料名" prop="mtrlName">
        <el-input
          v-model="queryParams.mtrlName"
          placeholder="请输入料名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料大类编码" prop="mtrlNoMain" label-width="100px">
        <el-input
          v-model="queryParams.mtrlNoMain"
          placeholder="请输入物料大类编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格型号" prop="mtrlSpec">
        <el-input
          v-model="queryParams.mtrlSpec"
          placeholder="请输入规格型号"
          clearable
          size="small"
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
          v-hasPermi="['stock:mtrl:add']"
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
          v-hasPermi="['stock:mtrl:edit']"
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
          v-hasPermi="['stock:mtrl:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['stock:mtrl:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
        v-loading="loading"
        :data="mtrlList"
        @selection-change="handleSelectionChange"
        border
        max-height="300"
        :header-cell-class-name="headerStyle"
        :row-class-name="rowClassName"
        :cell-class-name="cellClassName"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="料号" align="center" prop="mtrlNo" />
      <el-table-column label="料名" align="center" prop="mtrlName" />
      <el-table-column label="物料大类编码" align="center" prop="mtrlNoMain" />
      <el-table-column label="计量单位" align="center" prop="unit" :formatter="unitFormat" />
      <el-table-column label="品牌" align="center" prop="brand" />
      <el-table-column label="规格型号" align="center" prop="mtrlSpec" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['stock:mtrl:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['stock:mtrl:remove']"
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

    <!-- 添加或修改料号基本资料档对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="料名" prop="mtrlName">
          <el-input v-model="form.mtrlName" placeholder="请输入料名" />
        </el-form-item>
        <el-form-item label="物料大类编码" prop="mtrlNoMain">
          <el-input v-model="form.mtrlNoMain" placeholder="请输入物料大类编码" />
        </el-form-item>
        <el-form-item label="物料属性(M材料P备件)" prop="mtrlType">
          <el-input v-model="form.mtrlType" placeholder="请输入物料属性(M材料P备件)" />
        </el-form-item>
        <el-form-item label="计量单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入计量单位" />
        </el-form-item>
        <el-form-item label="品牌" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="规格型号" prop="mtrlSpec">
          <el-input v-model="form.mtrlSpec" placeholder="请输入规格型号" />
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
import { listMtrl, getMtrl, delMtrl, addMtrl, updateMtrl, exportMtrl } from "@/api/stock/mtrl";

export default {
  name: "Mtrl",
  components: {
  },
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
      // 料号基本资料档表格数据
      mtrlList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 计量单位字典
      unitOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mtrlName: null,
        mtrlNoMain: null,
        mtrlSpec: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // el-table行高调节
      rowClassName: "rowClass",
      cellClassName: "cellClass"
    };
  },
  created() {
    this.getList();
    this.getDicts("stock_unit_list").then(response => {
      this.unitOptions = response.data;
    });
  },
  methods: {
    /** 查询料号基本资料档列表 */
    getList() {
      this.loading = true;
      listMtrl(this.queryParams).then(response => {
        this.mtrlList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 计量单位字典翻译
    unitFormat(row, column) {
      return this.selectDictLabel(this.unitOptions, row.unit);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        mtrlNo: null,
        mtrlName: null,
        mtrlNoMain: null,
        mtrlNoNc: null,
        mtrlNameNc: null,
        mtrlType: null,
        unit: null,
        brand: null,
        mtrlSpec: null,
        companyId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.mtrlNo)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加料号基本资料档";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mtrlNo = row.mtrlNo || this.ids
      getMtrl(mtrlNo).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改料号基本资料档";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mtrlNo != null) {
            updateMtrl(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMtrl(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const mtrlNos = row.mtrlNo || this.ids;
      this.$confirm('是否确认删除料号基本资料档编号为"' + mtrlNos + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMtrl(mtrlNos);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有料号基本资料档数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMtrl(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    /** table的表头样式 */
    headerStyle({row, column, rowIndex, columnIndex}){
        return 'tableStyle';
    }
  }
};
</script>