package com.wxm.appbackend.usrmsg.data

import com.wxm.appbackend.usrmsg.data.UsrMsg
import org.springframework.data.repository.CrudRepository

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
interface UsrMsgRepository : CrudRepository<UsrMsg, Int>
