<h1>Парсер для арифметических выражений</h1>
Арифметические выражения с операциями сложения, вычитания, умножения, скобками, унарным минусом. Приоритет операций стандартный.
В качестве операндов выступают целые числа. Используйте один терминал для всех чисел. 
Пример: (1+2)*(-3*(7-4)+2)

<h2>Исходна грамматика</h2>
E -> E+T | E-T | T <br>
T -> T*E | F  <br>
F -> -F| (E) | n <br>

<h2>Посе устранения левой рекурси</h2>
E -> TU | T <br>
U -> +TU | -TU | +T | -T | e <br>
T -> FV | F <br>
V -> *EV | *E | e <br>
F -> -F| (E) | n <br>

<h2>множества FIRST и FOLLOW</h2>
FIRST: <br>
E  -> {- n (} <br>
U -> {+ - e} <br>
T  -> {-,n, (} <br>
V -> {*, e} <br>
F  -> {-, n, (} <br>

FOLLOW: <br>
E  -> {$, )} <br>
U -> {$, )} <br>
T  -> {$, +, -, )} <br>
V -> {$, +, -, )} <br>
F  -> {$, +, -, *, )} <br>

<h2>зависимости</h2>
graphviz или отдельно dot

    apt-get install graphviz
 