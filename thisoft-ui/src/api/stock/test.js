import request from '@/utils/request'

// 查询wq测试列表
export function listTest(query) {
  return request({
    url: '/stock/test/list',
    method: 'get',
    params: query
  })
}

// 查询wq测试详细
export function getTest(test01) {
  return request({
    url: '/stock/test/' + test01,
    method: 'get'
  })
}

// 新增wq测试
export function addTest(data) {
  return request({
    url: '/stock/test',
    method: 'post',
    data: data
  })
}

// 修改wq测试
export function updateTest(data) {
  return request({
    url: '/stock/test',
    method: 'put',
    data: data
  })
}

// 删除wq测试
export function delTest(test01) {
  return request({
    url: '/stock/test/' + test01,
    method: 'delete'
  })
}

// 导出wq测试
export function exportTest(query) {
  return request({
    url: '/stock/test/export',
    method: 'get',
    params: query
  })
}
