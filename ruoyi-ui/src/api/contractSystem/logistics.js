import request from '@/utils/request'

// 查询物流列表
export function listLogistics(query) {
  return request({
    url: '/logistic/logistics/list',
    method: 'get',
    params: query
  })
}

// 查询物流详细
export function getLogistics(id) {
  return request({
    url: '/logistic/logistics/' + id,
    method: 'get'
  })
}

// 新增物流
export function addLogistics(data) {
  return request({
    url: '/logistic/logistics',
    method: 'post',
    data: data
  })
}

// 修改物流
export function updateLogistics(data) {
  return request({
    url: '/logistic/logistics',
    method: 'put',
    data: data
  })
}

// 删除物流
export function delLogistics(id) {
  return request({
    url: '/logistic/logistics/' + id,
    method: 'delete'
  })
}
// 配送状态修改
export function changeStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/logistic/logistics/changeStatus',
    method: 'put',
    data: data
  })
}