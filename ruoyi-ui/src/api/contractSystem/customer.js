import request from '@/utils/request'

// 查询客户列表
export function listCustomer(query) {
  return request({
    url: '/tangba/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户详细
export function getCustomer(cId) {
  return request({
    url: '/tangba/customer/' + cId,
    method: 'get'
  })
}

// 新增客户
export function addCustomer(data) {
  return request({
    url: '/tangba/customer/add',
    method: 'post',
    data: data
  })
}

// 修改客户
export function updateCustomer(data) {
  return request({
    url: '/tangba/customer/update',
    method: 'put',
    data: data
  })
}

// 删除客户
export function delCustomer(cId) {
  return request({
    url: '/tangba/customer/' + cId,
    method: 'delete'
  })
}


// 用户状态修改changeCustomerStatus
export function changeCustomerStatus(cId, status) {
  const data = {
    cId,
    status
  }
  return request({
    url: '/tangba/customer/changeStatus',
    method: 'put',
    data: data
  })
}