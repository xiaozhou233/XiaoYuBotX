# XiaoYuBotX

[![GitHub commits](https://badgen.net/github/commits/xiaozhou233/XiaoYuBotX)](https://github.com/xiaozhou233/XiaoYuBotX/commits/master/)
[![GitHub latest commit](https://badgen.net/github/last-commit/xiaozhou233/XiaoYuBotX)](https://github.com/xiaozhou233/XiaoYuBotX/commits/master/)
[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://lbesson.mit-license.org/)

#### XiaoYuBotX (筱雨) 是一个基于 OneBot API 的 QQ 机器人框架。

------------

### 警告: 本项目仍处于开发阶段，不建议在生产环境中使用。API接口可能在未来版本中发生变化。

------------
## 如何构建
### **注意！java版本需要 17+**
```shell
    # 构建完整项目 用于正常使用(包含第三方库)
    ./gradlew shadowJar
```

```shell
    # 构建SDK 用于插件编写 (不包含第三方库)
    ./gradlew build
```

如果不出意外的话，输出文件在/out文件夹下(SDK则在/build/libs下)

## 如何使用
```shell
    java -jar XiaoYuBotX_<version>.jar
```
