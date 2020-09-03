#### Activity 相关说明（注意：所有的Activity都需要在AndroidManifest.xml文件配置，否则无法使用（跟Servlet一样都需要配置））
 - Activity Task Stack（Activity任务栈，用于存储Activity调用栈以及Activity信息。这是一个栈结构，先启动的Activity在最下面，最上面的Activity就是显示的那个）

#### Activity启动模式（启动Activity总是创建一个新对象，有时需要复用已有对象，可以在配置Activity时通过LaunchMode属性指定启动模式）
 - standard（标准模式，每次调用startActivity()函数就会产生一个新的实列）
 - singleTop（栈顶单列模式，如果已有一个实列在顶部时，就不会产生新的实列，如果不在栈顶就会产生一个新的实列）
 - singleTask（当前栈单列模式，只有一个实列，默认在当前Task中，这个就是标准的单列）
 - singleInstance(栈独立单例模式，只有一个实列且创建时会创建一个栈且此栈中除了这个Activity不能有其它对象)

#### Activity 应用相关（注意：使用Activity的Class跳转到另一个Activity叫显示意图，主要用于应用内的Activity相互跳转；使用ActivityActivity路径跳转到另一个Activity叫隐式意图，主要用于系统间的Activity跳转）
 - [Avtivity生命周期相关以及4种状态](https://github.com/firechiang/copycat/blob/master/copycat_helloword/src/main/java/com/firechiang/android/copycat_helloword/Activity03LifeCycle.java)
 - [使用Activity的Class跳转（显示意图）并监听回调（主要用于应用内的Activity相互跳转）](https://github.com/firechiang/copycat/blob/master/copycat_helloword/src/main/java/com/firechiang/android/copycat_helloword/Activity02LinearLayout01.java)
 - [点击按钮弹出Dialog](https://github.com/firechiang/copycat/blob/master/copycat_helloword/src/main/java/com/firechiang/android/copycat_helloword/Activity04Dialog01.java)
 - [线性布局，打电话，发短信以，通过Activity路径跳转（隐式意图，主要用于系统的Activity跳转），授权回调，长按事件](https://github.com/firechiang/copycat/blob/master/copycat_helloword/src/main/java/com/firechiang/android/copycat_helloword/Activity06TelephoneAndSendMsg.java)