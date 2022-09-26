import request from '@/utils/request'

// 查询员工列表
export function listEmp(query) {
  return request({
    url: '/tangba/emp/list',
    method: 'get',
    params: query
  })
}

// 查询员工详细
export function getEmp(eId) {
  return request({
    url: '/tangba/emp/' + eId,
    method: 'get'
  })
}

// 新增员工
export function addEmp(data) {
  return request({
    url: '/tangba/emp/add',
    method: 'post',
    data: data
  })
}

// 修改员工
export function updateEmp(data) {
  return request({
    url: '/tangba/emp/update',
    method: 'put',
    data: data
  })
}

// 删除员工
export function delEmp(eId) {
  return request({
    url: '/tangba/emp/' + eId,
    method: 'delete'
  })
}


// 用户状态修改changeUserStatus
export function changeTEmpStatus(eId, status) {
  const data = {
    eId,
    status
  }
  return request({
    url: '/tangba/emp/changeStatus',
    method: 'put',
    data: data
  })
}
