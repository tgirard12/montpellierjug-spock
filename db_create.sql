create DATABASE spock_demo;

CREATE EXTENSION "uuid-ossp";

CREATE TABLE public."SPEAKER"
(
  id_speaker UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name_ TEXT NOT NULL ,
  mail TEXT NOT NULL ,
  twitter TEXT
);

CREATE TABLE "TALK"
(
  id_talk UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  title TEXT NOT NULL ,
  description TEXT NOT NULL ,
  date_ TIMESTAMPTZ NOT NULL ,
  duration INTERVAL,
  speaker_fk UUID REFERENCES public."SPEAKER"
);