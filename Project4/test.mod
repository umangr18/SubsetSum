param n;
param weight{i in 1..n};
param target;
var x{1..n} binary;
maximize Weight:
        sum{i in 1..n} weight[i]*x[i];
subject to Constraint1:
        sum{i in 1..n} weight[i]*x[i] <= target;
subject to Constraint2:
        -1 * sum{i in 1..n} weight[i]*x[i] <= -1 * target;
