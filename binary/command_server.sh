nohup java -Dspring.profiles.active=bp -Dspring.jpa.properties.hibernate.hbm2ddl.auto=create -Dlog.file.location=$HOME/logs/marketplace-web -jar appliedmind-0.0.1-SNAPSHOT.jar </dev/null >nohup.out 2>nohup.err &

