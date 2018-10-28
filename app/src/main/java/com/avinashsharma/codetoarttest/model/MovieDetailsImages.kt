package com.avinashsharma.codetoarttest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetailsImages {

    @SerializedName("file_path")
    @Expose
    private var filePath: String? = null

    fun getFilePath(): String? {
        return filePath
    }

    fun setFilePath(filePath: String) {
        this.filePath = filePath
    }
}
