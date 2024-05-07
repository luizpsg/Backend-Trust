--
-- Name: tb_carro; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_carro (
    id bigint NOT NULL,
    ano_modelo character varying(255),
    cor character varying(255),
    marca character varying(255),
    modelo character varying(255),
    placa character varying(255),
    cliente_id bigint
);


--
-- Name: tb_carro_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_carro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_carro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tb_carro_id_seq OWNED BY public.tb_carro.id;


--
-- Name: tb_cliente; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_cliente (
    id bigint NOT NULL,
    cpf character varying(11),
    data_nascimento date NOT NULL,
    email character varying(255),
    nome character varying(255),
    senha character varying(30),
    telefone character varying(14)
);


--
-- Name: tb_cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tb_cliente_id_seq OWNED BY public.tb_cliente.id;


--
-- Name: tb_cotacao; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_cotacao (
    id bigint NOT NULL,
    arquivo_cotacao bytea,
    data_cotacao date,
    max_parcelas_boleto integer,
    max_parcelas_cartao integer,
    max_parcelas_cartao_especial integer,
    max_parcelas_debito_conta integer,
    max_parcelas_pix integer,
    nome_seguradora character varying(255),
    valor_cotacao double precision,
    carro_id bigint,
    cliente_id bigint
);


--
-- Name: tb_cotacao_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_cotacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_cotacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tb_cotacao_id_seq OWNED BY public.tb_cotacao.id;


--
-- Name: tb_endereco; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_endereco (
    id bigint NOT NULL,
    bairro character varying(255),
    cep character varying(255),
    cidade character varying(255),
    complemento character varying(255),
    estado character varying(255),
    numero character varying(255),
    rua character varying(255),
    cliente_id bigint
);


--
-- Name: tb_endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tb_endereco_id_seq OWNED BY public.tb_endereco.id;


--
-- Name: tb_carro id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_carro ALTER COLUMN id SET DEFAULT nextval('public.tb_carro_id_seq'::regclass);


--
-- Name: tb_cliente id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_cliente ALTER COLUMN id SET DEFAULT nextval('public.tb_cliente_id_seq'::regclass);


--
-- Name: tb_cotacao id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_cotacao ALTER COLUMN id SET DEFAULT nextval('public.tb_cotacao_id_seq'::regclass);


--
-- Name: tb_endereco id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_endereco ALTER COLUMN id SET DEFAULT nextval('public.tb_endereco_id_seq'::regclass);


--
-- Name: tb_carro tb_carro_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_carro
    ADD CONSTRAINT tb_carro_pkey PRIMARY KEY (id);


--
-- Name: tb_cliente tb_cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_cliente
    ADD CONSTRAINT tb_cliente_pkey PRIMARY KEY (id);


--
-- Name: tb_cotacao tb_cotacao_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_cotacao
    ADD CONSTRAINT tb_cotacao_pkey PRIMARY KEY (id);


--
-- Name: tb_endereco tb_endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_endereco
    ADD CONSTRAINT tb_endereco_pkey PRIMARY KEY (id);


--
-- Name: tb_cotacao fk6pntkxhutddodlhg2yvn25xrb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_cotacao
    ADD CONSTRAINT fk6pntkxhutddodlhg2yvn25xrb FOREIGN KEY (cliente_id) REFERENCES public.tb_cliente(id);


--
-- Name: tb_carro fkiv8ejv638u77x1oe7i25id263; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_carro
    ADD CONSTRAINT fkiv8ejv638u77x1oe7i25id263 FOREIGN KEY (cliente_id) REFERENCES public.tb_cliente(id);


--
-- Name: tb_endereco fktqe8u5ggrtf93dr80hiibdfd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_endereco
    ADD CONSTRAINT fktqe8u5ggrtf93dr80hiibdfd FOREIGN KEY (cliente_id) REFERENCES public.tb_cliente(id);


--
-- Name: tb_cotacao fkuarujvbsm7ufl6spqylwnjwu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_cotacao
    ADD CONSTRAINT fkuarujvbsm7ufl6spqylwnjwu FOREIGN KEY (carro_id) REFERENCES public.tb_carro(id);


--
-- PostgreSQL database dump complete
--

