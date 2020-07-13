CREATE TABLE CAMION(
    patente Varchar(7) PRIMARY KEY,
    marca_modelo Varchar(50),
    km_recorridos Integer,
    costo_por_km Float,
    costo_por_hora Float,
    fecha_compra DATE
);
CREATE TABLE PLANTA(
    nombre Varchar(50),
    id integer PRIMARY KEY
);
CREATE TABLE RUTA(
    id integer PRIMARY KEY,
    planta_origen integer references PLANTA(id),
    planta_destino integer references PLANTA(id),
    distancia_km float,
    duracion_hs float,
    peso_max_kg float
);
CREATE TABLE INSUMO(
    id integer PRIMARY KEY,
    descripcion varchar(100),
    unidad_medida varchar(8),
    costo float,
    peso float,
    densidad float
);
CREATE TABLE STOCK(
    id integer PRIMARY KEY,
    insumo integer references INSUMO(id),
    planta integer references PLANTA(id),
    cantidad integer,
    punto_pedido integer
);
CREATE TABLE ORDEN_PEDIDO(
    nro_orden integer PRIMARY KEY,
    planta_destino integer references PLANTA(id),
    fecha_solicitud DATE,
    fecha_entrega DATE,
    estado VARCHAR(10),
    camion varchar(7) references CAMION(patente),
    ruta integer references RUTA(id),
    costo_envio float
);
CREATE TABLE ORDEN_PEDIDO_ITEMS(
    nro_orden integer references ORDEN_PEDIDO(nro_orden),
    insumo integer references INSUMO(id),
    cantidad integer
)
CREATE TABLE PLANTA_CAMION(
    patente Varchar(7) references CAMION(patente),
    id_planta integer references PLANTA(id)
);