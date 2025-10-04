curl http://localhost:8080/usr/add \
--data @user-0.json \
--header "Content-Type: application/json" | aeson-pretty
echo
