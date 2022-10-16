create table public.users (
                              id integer primary key not null default nextval('users_id_seq'::regclass),
                              name character varying(255),
                              email character varying(255),
                              country character varying(255),
                              deleted boolean default false,
                              access boolean default false,
                              hour character varying(255),
                              salary character varying(255),
                              email_id bigint,
                              foreign key (email_id) references public.emails (id)
                                  match simple on update no action on delete no action
);
create table public.addresses (
                                  id bigint primary key not null default nextval('addresses_id_seq'::regclass),
                                  address_has_active boolean,
                                  city character varying(255),
                                  country character varying(255),
                                  street character varying(255),
                                  employee_id integer,
                                  foreign key (employee_id) references public.users (id)
                                      match simple on update no action on delete no action
);
create table public.emails (
                               id bigint primary key not null default nextval('emails_id_seq'::regclass),
                               email character varying(255),
                               email_has_active boolean
);