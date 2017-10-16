# FROM clojure:alpine AS build
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
# COPY ./project.clj /usr/src/app/
# COPY ./src/ /usr/src/app/src/
# RUN lein deps
# RUN lein cljsbuild once

FROM clojure:alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
EXPOSE 3000
COPY ./project.clj /usr/src/app/
RUN mkdir /usr/src/app/resources
# COPY --from=build /usr/src/app/resources/ /usr/src/app/resources/ 
# COPY . /usr/src/app/
VOLUME [ "/usr/src/app/src" "/usr/src/app/resources"]
RUN lein deps
CMD ["lein", "ring", "server-headless"]
