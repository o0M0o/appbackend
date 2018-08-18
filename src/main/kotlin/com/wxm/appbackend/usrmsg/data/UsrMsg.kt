package com.wxm.appbackend.usrmsg.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * for usr message
 * [msg] is message send by usr
 * [usr] is identification usr
 * [replyAddress] is for reply usr
 */
@Entity // This tells Hibernate to make a table out of this class
class UsrMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    var msg: String = ""
    var usr: String = ""
    var replyAddress: String = ""
}