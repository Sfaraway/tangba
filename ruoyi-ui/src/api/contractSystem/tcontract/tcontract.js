import request from '@/utils/request'

// 查询合同列表
export function listTcontract(query) {
  return request({
    url: '/contractSystem/tcontract/list',
    method: 'get',
    params: query
  })
}

// 查询合同详细
export function getTcontract(id) {
  return request({
    url: '/contractSystem/tcontract/' + id,
    method: 'get'
  })
}

// 新增合同
export function addTcontract(data) {
  return request({
    url: '/contractSystem/tcontract',
    method: 'post',
    data: data
  })
}

// 修改合同
export function updateTcontract(data) {
  return request({
    url: '/contractSystem/tcontract',
    method: 'put',
    data: data
  })
}

// 删除合同
export function delTcontract(id) {
  return request({
    url: '/contractSystem/tcontract/' + id,
    method: 'delete'
  })
}

export function getListMapF(query) {
  return request({
    url: '/contractSystem/tcontract/getstaffposition',
    method: 'get',
    params: query
  })
}
