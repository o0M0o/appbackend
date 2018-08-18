@file:Suppress("unused")
package com.wxm.appbackend.utility


object MachineStatus {

    /**
     * get system type : windows or linux or other
     */
    private fun isWindowsOrLinux(): String {
        val osName = System.getProperty("os.name")
        return when {
            osName.toLowerCase().startsWith("windows") -> "windows"
            osName.toLowerCase().startsWith("linux") -> "linux"
            else -> ""
        }
    }
}