package com.alex.hoo.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.alex.hoo.common.BaseConstant.OUTPUT_PATH
import com.alex.hoo.utils.makeStatusNotification
import java.io.File

class CleanUpWorker(ctx: Context, params: WorkerParameters): Worker(ctx, params) {

    private val TAG by lazy {
        this::class.java.simpleName
    }

    override fun doWork(): Result {
        makeStatusNotification("Cleaning up old temporary files", applicationContext)
        return try {
            // 删除逻辑
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
            if (outputDirectory.exists()){
                val entries = outputDirectory.listFiles()
                if (entries != null){
                    for (entry in entries){
                        val name = entry.name
                        if(name.isNotEmpty() && name.endsWith(".png")){
                            val deleted = entry.delete()
                        }
                    }
                }
            }
            // 成功时返回
            Result.success()
        }catch (e: Exception){
            Result.failure()
        }
    }


}