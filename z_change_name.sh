# this script initializes a new microservice application with correct names ready 
# to be deployed with {config,discovery,gateway}-services (along with anything new)
# having changed their appname and db creds for the new environment

# reading creds
echo "Application Name:"
x_appname=$(python3 -c "print(input())")

echo "Database Name:"
x_dbname=$(python3 -c "print(input())")

echo "Username for DB:"
x_username=$(python3 -c "print(input())")

echo "Password for DB:" 
x_password=$(python3 -c "print(input())")

# inline editing everything
# grep -Hnre "XX_APP" | awk '{print $1}' | awk 'BEGIN{FS=":";} {print "./" $1}' | uniq
grep --color=NEVER -Hr "XX_APP_NAME" | awk '{print $1}' | awk 'BEGIN{FS=":";} {print "./" $1}' | uniq | xargs sed -i "s/XX_APP_NAME/$x_appname/g"
grep --color=NEVER -Hr "XX_APP_USERNAME" | awk '{print $1}' | awk 'BEGIN{FS=":";} {print "./" $1}' | uniq | xargs sed -i "s/XX_APP_USERNAME/$x_username/g"
grep --color=NEVER -Hr "XX_APP_PASSWORD" | awk '{print $1}' | awk 'BEGIN{FS=":";} {print "./" $1}' | uniq | xargs sed -i "s/XX_APP_PASSWORD/$x_password/g"
grep --color=NEVER -Hr "XX_APP_DBNAME" | awk '{print $1}' | awk 'BEGIN{FS=":";} {print "./" $1}' | uniq | xargs sed -i "s/XX_APP_DBNAME/$x_dbname/g"

# changing directory names
dirs=$(find . -name \*XX_APP_NAME\* | xargs realpath )
for d in $dirs; do
	destname=$(echo $d| sed "s/XX_APP_NAME/$x_appname/")
	mv $d $destname
done

rm -rf .git
rm .gitignore
