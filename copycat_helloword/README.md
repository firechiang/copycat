#### Activity 相关说明
 - Activity Task Stack（Activity任务栈，用于存储Activity调用栈以及Activity信息。这是一个栈结构，先启动的Activity在最下面，最上面的Activity就是显示的那个）

#### Activity启动模式（启动Activity总是创建一个新对象，有时需要复用已有对象，可以在配置Activity时通过LaunchMode属性指定启动模式）
 - standard（标准模式，每次调用startActivity()函数就会产生一个新的实列）
 - singleTop（栈顶单列模式，如果已有一个实列在顶部时，就不会产生新的实列，如果不在栈顶就会产生一个新的实列）
 - singleTask（当前栈单列模式，只有一个实列，默认在当前Task中，这个就是标准的单列）
 - singleInstance(栈独立单例模式，只有一个实列且创建时会创建一个栈且此栈中除了这个Activity不能有其它对象)