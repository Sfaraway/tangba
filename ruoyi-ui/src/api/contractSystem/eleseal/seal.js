import request from '@/utils/request'

// 查询印章列表
export function listSeal(query) {
  return request({
    url: '/eleseal/seal/list',
    method: 'get',
    params: query
  })
}

// 查询印章详细
export function getSeal(id) {
  return request({
    url: '/eleseal/seal/' + id,
    method: 'get'
  })
}

// 新增印章
export function addSeal(data) {
  data.updateTime = Date.now();
  return request({
    url: '/eleseal/seal',
    method: 'post',
    data: data
  })
}

// 修改印章
export function updateSeal(data) {
  return request({
    url: '/eleseal/seal',
    method: 'put',
    data: data
  })
}

// 删除印章
export function delSeal(id) {
  return request({
    url: '/eleseal/seal/' + id,
    method: 'delete'
  })
}

// 印章状态修改
export function changeSealStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/eleseal/seal/changeSealStatus',
    method: 'put',
    data: data
  })
}
