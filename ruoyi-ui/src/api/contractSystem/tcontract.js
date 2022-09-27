import request from '@/utils/request'

// 查询合同列表
export function listContract(query) {
  return request({
    url: '/tcon/contract/list',
    method: 'get',
    params: query
  })
}

// 查询合同详细
export function getContract(id) {
  return request({
    url: '/tcon/contract/' + id,
    method: 'get'
  })
}

// 新增合同
export function addContract(data) {
  return request({
    url: '/tcon/contract',
    method: 'post',
    data: data
  })
}

// 修改合同
export function updateContract(data) {
  return request({
    url: '/tcon/contract',
    method: 'put',
    data: data
  })
}

// 删除合同
export function delContract(id) {
  return request({
    url: '/tcon/contract/' + id,
    method: 'delete'
  })
}

export function changeContractStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/tcon/contract/changeStatus',
    method: 'put',
    data: data
  })
}

export function changeContractStatusTwo(id, sealStatus ) {
  const data = {
    id,
    sealStatus
  }
  return request({
    url: '/tcon/contract/changeStatusTwo',
    method: 'put',
    data: data
  })
}
