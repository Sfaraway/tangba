// 获取当前登录用户
import request from "@/utils/request";
import { getEmp } from "@/api/contractSystem/emp"

export function forceLogout(tokenId) {
  return request({
    url: '/monitor/online/' + tokenId,
    method: 'delete'
  })
}

/*
* 获取当前用户ID
* */
export function getUserId() {
  return request({
    url: '/contract/common/userId',
    method: 'get'
  })
}

/*
* addUserId   addUserName
* updateUserId
* */
export function getEmpById(eId, elem) {
  getEmp(eId).then(response => {
    elem.addUserName = response.data.ename;
    elem.updateUserName = response.data.ename;
  })
}


