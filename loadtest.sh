
# RG=xkvExppvsuyh
RG=qcfdwspsgjya

mvn gatling:test -Dgatling.simulationClass=cloud.intermission.quarkus.AmortizationLoadTest -DtargetUrl=https://$RG-functions-quarkus.azurewebsites.net
