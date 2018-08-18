package com.wxm.appbackend.status

/**
 * status for backend machine
 * [id] for distinguish data
 * [cpuUsage] for cpu usage, range [0, 100]
 * [memUsage] for cpu usage, range [0, 100]
 */
data class Status(val id: Long, val cpuUsage: Double, val memUsage: Double)