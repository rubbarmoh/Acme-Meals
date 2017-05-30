start transaction;

use `Acme-Meals`;

revoke all privileges on `Acme-Meals`.* from 'acme-user'@'%';
revoke all privileges on `Acme-Meals`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Meals`;

commit;