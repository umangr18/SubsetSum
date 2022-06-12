set E;
param weight{i in E};
param target;

var x{i in E} binary;

maximize Weight:
        sum{i in E} weight[i]*x[i];
subject to Constraint1:
        sum{i in E} weight[i]*x[i] <= target;
subject to Constraint2:
        -1 * sum{i in E} weight[i]*x[i] <= -1 * target;
