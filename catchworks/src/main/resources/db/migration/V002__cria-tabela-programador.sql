CREATE TABLE Programador(
	id bigint not null auto_increment,
    login varchar(100) not null,
    urlPerfil text not null,
    Primary Key (id)
)Engine = InnoDB;