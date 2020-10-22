# DesafioAnalista

O projeto foi todo desenvolvido em Java com Spring Boot, utilizando a IDE Spring Tool Suite 4.

Para executar o mesmo é necessário configurar a url de conexão do banco de dados no application e executar o projeto.

consegui apenas fazer a parte de usuário, cadastro, listagem, atualização e deletar, o restante apesar de tentar não consegui terminar.

consegui acessar a Api do Git Hub, encontrei a busca que queria, mas não consegui consumir o Json que traz a consulta

"https://api.github.com/search/users?q=type:user+followers:%3E3419+repos:%3E0&sort=followers&order=desc"

A consulta acima, testa se o usuário é do tipo user, se ele tem seguidores mais seguidores que "3419", se ele tem repositorio maior que 0, ou seja, ele teria que ter pelomenos 1 repositório para aparecer, sendo em ordem decrescente e listando por quantidade de seguidores.