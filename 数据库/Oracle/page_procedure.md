
```
create or replace procedure pagingPro(v_in_table in varchar2,v_in_pagesiez in number,v_in_pagenow in number,
v_out_result out pack1.my_cursor,v_out_rows out number,v_out_pagecount out number)is
--定义变量
v_sql varchar2(2000);
v_start number;
v_end number;
v_rows_cursor pack1.my_cursor;
begin
--执行分页代码
v_start:= v_in_pagesiez * (v_in_pagenow - 1) + 1;
v_end:= v_in_pagesiez * v_in_pagenow;
v_sql:='select t2.* from (select t1.*,rownum rn from(select * from '||v_in_table||')t1 where rownum<='||v_end||') t2 where rn>='||v_start;
--打开游标,让游标指向结果集
open v_out_result for v_sql;
--查询共有多少条记录
v_sql:='select count(*) from ' ||v_in_table;
open v_rows_cursor for v_sql;
loop
fetch v_rows_cursor into v_out_rows;
exit when v_rows_cursor%notfound;
end loop;
close v_rows_cursor;
if mod(v_out_rows,v_in_pagesiez)=0 then v_out_pagecount:=v_out_rows/v_in_pagesiez;
else v_out_pagecount:=v_out_rows/v_in_pagesiez + 1; 
end if;
end;
/
```

