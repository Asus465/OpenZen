# Open Zen [WIP]

> 截止至目前，Open Zen仍然处于早期构建阶段。大量特性可能仍不可用，请谅解！当您发现有不可用的功能，欢迎开Issues描述或直接提交Pull Request，我们将不胜感激！

**Open Zen** 是 *Zen* Minecraft 客户端的反混淆源码版本，几乎是由 [Claude](https://claude.com/)协助逆向得到。目标版本为 **Minecraft 1.20.1 + Forge 47.4.20**。

原始 Jar 经过完整混淆：类/字段/方法重命名、控制流扁平化。我使用Opus 4.7对其进行了反混淆，并结合 [Enigma MCP](https://github.com/Margele/Enigma-MCP)和Sonnet 4.6对其的类/字段/方法重命名进行猜测，最后将其还原为了可读的 Java。最终产物是一个可以直接用 Gradle 构建的工程，而不是一个二进制 blob。

> ⚠️ 本仓库**仅供学习与研究目的发布** —— 用于研究客户端侧游戏改造、ASM 字节码补丁和混淆/反混淆技术。在你不拥有的服务器上使用作弊客户端违反绝大多数服务器规则，请自行承担后果。

## 许可
原始混淆字节码未授予任何许可。本仓库中的反混淆产物、构建脚本与文档**仅供研究与学习使用**。如果你是 Zen 的原作者并希望本仓库下架或重新授权，请提 Issues。虽然提了也不会搭理你。

## 截图
![Open Zen ClickGUI](img/screenshot.png)

## TODO
 - 直接构建为热注入DLL。
 - 你来提出Issue，我来解决。

## 原始 Jar + Mapping
[原始Jar](./mapping/zen-orignial.jar)
[Mapping](./mapping/zen.mapping)

需要说明的是，部分喜欢裸舞的忠实Zen用户认为本源码逆向自比较旧的Zen版本。可能是因为这部分用户的脑容量只允许自己导入其他配置，因此不认识Zen的老旧UI。因此必须要说明，此源码使用的原始Jar截止至2026年5月21日是最新的。

## 细节
经过Opus 4.7长达18秒的分析，Opus认为所有的类由惨遭魔改的Zelix KlassMaster混淆。除了Zelix的Invoke Dynamic和String Encryption外，还有部分未参与任何计算的Interger \ Long变量花指令代码和仅在部分方法中出现的Flow混淆。

其中大部分类都可以经过小修小补的现有Zelix反混淆器完成，关键部分的`cinit`被Native保护，导致在Java层中没有对应的Master Key可以对Invoke Dynamic和String反混淆。但可能由于精神马来西亚人的脑袋在马来西亚骑摩托被其他车创飞导致脑溢血，即使你没有通过客户端认证也可以完整加载Native并对Class进行注册。因此我们完整的还原了所有类的Invoke Dynamic和String混淆。

其他的混淆经过Opus长达30秒的分析，顺利写出了反混淆器。 但被Rename后的代码几乎不可读，因此我用Opus 4.7制作了[Enigma MCP](https://github.com/Margele/Enigma-MCP)，接入Sonnet 4.6对其参照部分客户端进行了反混淆。

再使用Opus 4.7对本项目经过长达6小时的修复和少量的人工修复，便得到了这份源码。

## 后门
原版的Zen存在大量后门，例如上报QQ、屏幕截图、扫描文件、上传文件、远程执行命令等。因此我们不推荐任何用户继续使用原版Zen，除非你愿意现在把你身上的衣服脱掉然后去本地人最多的广场裸舞，然后把自己裸舞的视频发送到Zen的群内。

> Zen开发者称，所有的后门都未实现未引用，是由夏天完成的。但笔者在分析其Native时发现了完整引用，暂且蒙古。

经过分析，当你登录Zen后就会自动触发屏幕截图。同时已有用户反馈，当你狗叫Zen开发者后，其开发者将会把你的电脑屏幕截图发送至QQ群内，所以请你不要狗叫Zen开发者。

[详细分析](./mapping/BACKDOOR.md)

## 抄袭
此项目大部分功能模块几乎全部抄袭自Naven客户端，具体详见以下分析。

[详细分析](./paste/README.md)

## 状态与注意事项
- 这是**尽力而为的反混淆结果**，部分符号是根据上下文重建的，可能与原作者的命名意图不一致。

## 致谢

- 原始混淆客户端：**Zen**。
- 反混淆、符号还原与工程脚手架：**Claude** 在人工监督下完成。
- [Java Deobfuscator](https://github.com/java-deobfuscator/deobfuscator)
- 从古墓中挖出的 [Themida](https://www.oreans.com/Themida.php)
- 惨遭魔改的 [Zelix](https://www.zelix.com/)
- [Enigma MCP](https://github.com/Margele/Enigma-MCP)