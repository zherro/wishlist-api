docker rm -f wishlist-api zherro/wishlist-api \
&& docker rmi -f wishlist-api zherro/wishlist-api \
&& ./gradlew clean build \
&& docker login \
&& docker build -t wishlist-api . \
&& docker tag wishlist-api:latest zherro/wishlist-api:latest \
&& docker push zherro/wishlist-api:latest \
&& docker pull zherro/wishlist-api:latest
