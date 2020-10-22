Create Table Repositorio(
	id bigint not null auto_increment,
    privado bool not null,
    programador_id bigint not null,
    Primary Key (id),
    Foreign Key (programador_id) References programador (id)
)Engine = InnoDB;