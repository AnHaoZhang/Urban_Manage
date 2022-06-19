<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="地址名称" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经度" prop="lng">
        <el-input
          v-model="queryParams.lng"
          placeholder="请输入经度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纬度" prop="lat">
        <el-input
          v-model="queryParams.lat"
          placeholder="请输入纬度"
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
          v-hasPermi="['function:stall:add']"
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
          v-hasPermi="['function:stall:edit']"
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
          v-hasPermi="['function:stall:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['function:stall:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-upload"
          size="mini"
          @click="handleImport"
          v-hasPermi="['function:stall:import']"
        >导入</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading"  :data="stallList" @selection-change="handleSelectionChange">
      <el-table-column type="selection"  align="center" />
      <el-table-column label="摊位编号" width="100" align="center" prop="stallsId" />
      <el-table-column label="摊位类型" width="100" align="center" prop="stallsType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_stall_type" :value="scope.row.stallsType"/>
        </template>
      </el-table-column>

      <el-table-column label="地址名称"  width="100" align="center" prop="address" />
      <el-table-column label="经度" width="105" align="center" prop="lng" />
      <el-table-column label="纬度" width="105" align="center" prop="lat" />
      <el-table-column label="状态" width="105" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_stall_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" width="110" align="center" prop="remark" />
      <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit-outline"
            @click="handleOrder(scope.row)"
            v-hasPermi="['function:stall:order']"
          >预约</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['function:stall:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['function:stall:remove']"
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

    <!-- 添加或修改摊位信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="地址名称" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址名称" />
        </el-form-item>
<!--        <el-form-item label="摊位状态" prop="status">-->
<!--          <el-radio-group v-model="form.status">-->
<!--            <el-radio-->
<!--              v-for="dict in dict.type.sys_stall_status"-->
<!--              :key="dict.value"-->
<!--              :label="dict.value"-->
<!--            >{{dict.label}}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
        <el-form-item label="经度" prop="lng">
          <el-input v-model="form.lng" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="lat">
          <el-input v-model="form.lat" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 摊位预约信息对话框 -->
    <el-dialog class="formstall" :title="title" :visible.sync="order" width="500px" append-to-body>
      <el-form ref="formstall" :model="formstall" :rules="rules" label-width="80px">
        <el-form-item label="地址名称" prop="address">
          <el-input v-model="formstall.address" placeholder="请输入地址名称" />
        </el-form-item>
        <el-form-item label="摊位状态" prop="status">
          <el-radio-group v-model="formstall.status">
          <el-radio
            v-for="dict in dict.type.sys_stall_status"
            :key="dict.value"
            :label="dict.value"
          >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="经度" prop="lng">
          <el-input v-model="formstall.lng" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="lat">
          <el-input v-model="formstall.lat" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="联系方式" prop="remark">
          <el-input v-model="formstall.remark" type="textarea" placeholder="请输入联系方式" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitOrder">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 摊位导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的摊位数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStall, getStall, delStall, addStall, updateStall } from "@/api/function/stall";

import {getToken} from "@/utils/auth";

export default {
  name: "Stall",
  dicts: ['sys_stall_status', 'sys_stall_type'],
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
      // 摊位信息表格数据
      stallList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      order:false,
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        order:false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/function/stall/importStall"
      },

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stallsType: null,
        address: null,
        lng: null,
        lat: null,
        status: null,
      },
      // 表单参数
      form: {},
      formstall:{},
      // 表单校验
      rules: {
        stallsType: [
          { required: true, message: "摊位类型不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "地址名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询摊位信息列表 */
    getList() {
      this.loading = true;
      listStall(this.queryParams).then(response => {
        this.stallList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.order = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        stallsId: null,
        stallsType: "0",
        address: null,
        lng: null,
        lat: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },

    resets() {
      this.formstall = {
        stallsId: null,
        stallsType: "0",
        address: null,
        lng: null,
        lat: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.stallsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加摊位信息";
    },
    /** 预约按钮操作 */
    handleOrder(row) {
      this.resets();
      const stallsId = row.stallsId || this.ids
      getStall(stallsId).then(response => {
        this.formstall = response.data;
        this.order = true;
        this.title = "摊位预约";
      });
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stallsId = row.stallsId || this.ids
      getStall(stallsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改摊位信息";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stallsId != null) {
            updateStall(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStall(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 预约按钮*/
    submitOrder(){
      this.$refs["formstall"].validate(valid =>{
        if(valid){
          if ( (this.formstall.stallsId != null)&&(this.formstall.status !=1) ) {
            if((this.formstall.remark != null)&&(this.formstall.remark != '')){
              this.formstall.status=1;
              updateStall(this.formstall).then(response => {
                  this.$modal.msgSuccess("预约成功");
                  this.order= false;
                  this.getList();
              });
            }else{
              this.$modal.msgSuccess("请填写联系方式");
            }
          }
          else {
              this.$modal.msgSuccess("预约失败");
          }
        }
      })

    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const stallsIds = row.stallsId || this.ids;
      this.$modal.confirm('是否确认删除摊位信息编号为"' + stallsIds + '"的数据项？').then(function() {
        return delStall(stallsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('function/stall/export', {
        ...this.queryParams
      }, `stall_${new Date().getTime()}.xlsx`)
    },

    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "摊位导入";
      this.upload.open = true;
    },
    /** 下载模板操作*/
    importTemplate() {
      // importTemplate().then(response => {
      //   this.download(response.msg);
      // });
      this.download('function/stall/importTemplate', {
      }, `stall_template_${new Date().getTime()}.xlsx`)
    },
    /** 文件上传中处理*/
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
  //  文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg,"导入结果",{dangerouslyUseHTMLString:true});
      this.getList();
    },
  //  提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
