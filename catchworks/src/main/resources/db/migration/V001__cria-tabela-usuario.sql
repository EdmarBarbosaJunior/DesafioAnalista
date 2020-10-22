CREATE TABLE Usuario(
	id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(255) not null,
    senha text not null,
    Primary Key (id)
)Engine = InnoDB;