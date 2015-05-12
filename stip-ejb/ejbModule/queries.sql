--reference queries not used any where
delete from stip_dept where stipdept_id> 392;

update stip_dept d1,(
select * from stipDept_8_13_14  where id in (
45,
50,
234,
325,
334
) ) d2

set d1.indx=d2.inde_,d1.fund=d2.fund,d1.org=d2.org,d1.notes=d2.notes

 where d1.stipdept_id=d2.id;

update stip_dept set status=1;

update stip_dept set division='School of Pharmacy' where stipdept_id in(261,262);

/


insert into stip_dept (dept,division,stip_fund,indx,fund,org,acct,notes,status)

SELECT SUBSTR(Dept,5),Division,StipFund,Inde_,Fund,Org,Acct,Notes,1 from stipdept
/
urce Management Resource Management
Control SOM Control
/
update  stip_dept set dept='SOM Control' where dept='Control'
/
truncate stip_dept_ctrl
/
insert into  stip_dept_ctrl
select * from stip_dept where dept='SOM Control'
/
delete from stip_dept where dept='SOM Control'
/
stip_dept

Family Medicine to Family & Preventive Medicine
Repro Med to Reproductive Medicine

/
//mismatch info
  update stipdetail set major_group='Shared Corp Offices'
      where qrt=inqrt and inde_ is  null and portion_stip <> 0 and major_group='Medical Group' and department='Telemedicine';
/