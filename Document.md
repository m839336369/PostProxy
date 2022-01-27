一、**使用流程**
==========

（一）**运行**
---------

*   上传文件至{path}目录
*   执行：java -jar {path}/PostProxy.jar
*   结束：sudo fuser -k -n tcp 8080（端口号）

（二）**配置**
---------

*   程序首次启动，会在指定目录生成配置文件
*   目录： /root/ProxyConfig
*   配置该目录文件
*   重新启动程序

二、**Config配置项介绍**
=================

（一）**概览**
------------

```
tokens:
  Replace:
    params: {}
    resultProcesses:
    - {operate: Replace, regex: '"描述价格": "(\d*)"', value: '"描述价格": "99999"'}
    url: https://api.iyk0.com/sjzx/?msg=苹果12
  Api2:
    params: {token: 558ffa8177ac5748869c4cd1c93d57ab}
    resultProcesses: []
    url: http://api2.ronsir.cn/v1/email/send
  Ratio:
    params: {}
    resultProcesses:
    - {operate: Ratio, regex: '"描述价格": "(\d*)"', value: '0.5'}
    url: https://api.iyk0.com/sjzx/?msg=苹果12
  菜谱查询:
    params: {p: '1', key: 白菜, n: '1'}
    resultProcesses: []
    url: https://api.iyk0.com/shipu/
```
（二）**Tokens**
-------------

http://127.0.0.1:8080/request?token=Ratio

http://127.0.0.1:8080/request?token=Api2

http://127.0.0.1:8080/request?token=Replace

http://127.0.0.1:8080/request?token=菜谱查询

表明选择token执行对应token代理操作

（三）**Params**
-------------

需要替换的参数，如果参数不存在则补全

（四）**ResultProcesses**
--------------------------

操作链，支持多操作，依次处理，分为operate（操作符）、regex（正则目标）、（value）对应操作所需值

（五）**Url**
----------

为用户代理的目标地址