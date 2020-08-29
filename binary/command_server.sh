nohup java -Dspring.profiles.active=bp -Dspring.jpa.properties.hibernate.hbm2ddl.auto=update -Dlog.file.location=$HOME/logs/marketplace-web -jar GharPe-1.0.0.jar </dev/null >nohup.out 2>nohup.err &

