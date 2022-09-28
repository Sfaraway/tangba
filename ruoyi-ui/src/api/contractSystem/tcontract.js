import request from '@/utils/request'

// 查询合同列表
export function listContract(query) {
  return request({
    url: '/contractSystem/tcontract/list',
    method: 'get',
    params: query
  })
}

// 查询合同详细
export function getContract(id) {
  return request({
    url: '/contractSystem/tcontract/' + id,
    method: 'get'
  })
}

// 新增合同
export function addContract(data) {
  return request({
    url: '/contractSystem/tcontract/',
    method: 'post',
    data: data
  })
}

// 修改合同
export function updateContract(data) {
  return request({
    url: '/contractSystem/tcontract',
    method: 'put',
    data: data
  })
}

// 删除合同
export function delContract(id) {
  return request({
    url: '/contractSystem/tcontract/' + id,
    method: 'delete'
  })
}

export function changeContractStatus(id, status, contractStatus) {
  const data = {
    id,
    status,
    contractStatus
  }
  return request({
    url: '/contractSystem/tcontract/changeStatus',
    method: 'put',
    data: data
  })
}

export function changeContractStatusTwo(id, accessStu ,contractStatus) {
  const data = {
    id,
    accessStu,
    contractStatus

  }
  return request({
    url: '/contractSystem/tcontract/changeAccessStatus',
    method: 'put',
    data: data
  })
}
