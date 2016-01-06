module.exports = function(grunt) {
    grunt.initConfig({
        simplemocha: {
            options: {
                globals: ['should'],
                timeout: 3000,
                ignoreLeaks: false,
                ui: 'tdd',
                reporter: 'nyan'
            },
            all: {
                src: ['test/**/*.js']
            }
        },
        watch: {
            files: ['src/**/*.js', 'test/**/*.js'],
            tasks: ['simplemocha']
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-simple-mocha');
};

