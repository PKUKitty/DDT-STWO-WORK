#!/bin/bash

readonly ROOT_DIR="$1"
function travelFolder(){
    for f in `ls $1`
    do
        dir_or_file="$1/${f}"
        if [ -f ${dir_or_file} ]
        then
            if [ "${dir_or_file##*.}" = "h" ]
            then
                file_no_suffix="${dir_or_file%%.*}"
                base_file_no_suffix=`basename ${file_no_suffix}`

                cat ${dir_or_file} >> ~/eqf.docx
                src_file="${ROOT_DIR}/src/${base_file_no_suffix}.cpp"
                if [ -f ${src_file} ]
                then
                    cat ${src_file} >> ~/eqf.docx
                fi

                echo ${dir_or_file} is file
            fi
        fi

        if [ -d ${dir_or_file} ]
        then
            travelFolder ${dir_or_file}
        fi
    done
}

travelFolder $1

sed -i '/^$/d' ~/eqf.docx
sed -i 's/yujun/***/g' ~/eqf.docx
sed -i 's/liuxiaobin/***/g' ~/eqf.docx
sed -i 's/yaotingting/***/g' ~/eqf.docx
