package com.sa.demo.exoxycarouseldemo.base.model.remote.result

import com.sa.demo.exoxycarouseldemo.base.model.remote.result.BaseResult
import okhttp3.ResponseBody

/**
 * Created by Alpesh Rathod on 4/12/19.
 */
class DownloadFileResult(var body: ResponseBody, var previewable: Boolean = false) : BaseResult()