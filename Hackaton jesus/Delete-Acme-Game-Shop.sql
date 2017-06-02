start transaction;

use `Acme-Game-Shop`;

revoke all privileges on `Acme-Game-Shop`.* from 'acme-user'@'%';
revoke all privileges on `Acme-Game-Shop`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Game-Shop`;

commit;