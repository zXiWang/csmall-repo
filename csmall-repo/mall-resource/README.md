# mall-resource说明文档

## 1. 基本介绍

此模块是用于处理文件上传的Web服务模块，将提供文件上传服务和访问上传的文件的服务。

提示：当前可上传的文件类型或定位有：

- 商品图片
- 类别图标
- 品牌LOGO

## 2. 配置说明

此模块中包含如下配置文件，均在项目的【src\main\resources】之下：

- 【application.yml】 主配置文件
- 【application-dev.yml】 开发环境下的配置文件，应由【开发】人员调整此文件中的配置
- 【application-prod.yml】 生产（正式上线）环境下的配置文件，应由【运维】人员调整此文件中的配置
- 【application-test.yml】 测试环境下的配置文件，应由【测试】人员调整此文件中的配置

在没有明确说明的情况下，在主配置文件中的是【不应该】被修改的配置，这些配置【始终】会被加载。

其它配置文件中的配置仅当【被激活】后才可以生效，你可以在主配置文件中修改【spring.profiles.active】的值，以激活某个主配置文件以外的配置文件。

例如：如果你是【测试】人员，你应该只修改【application-test.yml】中的配置，并且，将主配置文件中的【spring.profiles.active】的值改为【test】。

你可能需要关心上传文件的相关配置，请参见你对应的【application-xxx.yml】文件中的注释说明。

注意：无论你修改了哪项配置，务必使得【属性名右侧的冒号】与【属性值】之间有【1个空格】，并且，【不能】随意调整配置文件源代码的缩进！

## 3. 如何启动

此模块可以独立启动，并不依赖于其它服务，除非你使用了其它技术访问上传的文件。

此模块启动时，将默认占用【9060】端口，如果你希望使用其它端口，请根据你的角色调整对应的配置文件中的【server.port】属性，并在主配置文件中激活你的配置。

此模块提供了在线API文档，你可以通过根路径下的 doc.html 进行访问，例如：http://localhost:9060/doc.html。