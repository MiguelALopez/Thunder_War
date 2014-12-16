DROP TABLE puntajes;
DROP TABLE usuario;

CREATE TABLE usuario (
  nick_name VARCHAR(30) NOT NULL PRIMARY KEY,
  password  VARCHAR(30) NOT NULL,
  nombre    VARCHAR(50) NOT NULL,
  apellido  VARCHAR(50) NOT NULL,
  tipo_nave INT         NOT NULL,
  sonido    BOOLEAN     NOT NULL,
  estado    BOOLEAN     NOT NULL
);

CREATE TABLE puntajes(
  nick_name_user VARCHAR(30) NOT NULL,
  puntaje INT NOT NULL,
  FOREIGN KEY (nick_name_user) REFERENCES usuario(nick_name)
);

INSERT INTO usuario(nick_name, password, nombre, apellido, tipo_nave, sonido, estado)
    VALUES ('miguelalf', '123456', 'miguel', 'lopez', 1, FALSE, TRUE );

INSERT INTO usuario(nick_name, password, nombre, apellido, tipo_nave, sonido, estado)
VALUES ('pepito', '123456', 'pepito', 'perez', 1, FALSE, TRUE);

INSERT INTO puntajes(nick_name_user, puntaje)
    VALUES ('miguelalf', 10000);

INSERT INTO puntajes(nick_name_user, puntaje)
VALUES ('miguelalf', 200000);

INSERT INTO puntajes(nick_name_user, puntaje)
VALUES ('pepito', 500000);